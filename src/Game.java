import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
    public final int noOfLevels = 3;

    private GameLevel gameLevel;
    private UserView view;
    private GameLevel[] levels;
    private int levelIndex;
    private JFrame gameJFrame;
    private JFrame mainMenuJFrame;
    private SoundClip soundtrack;
    private SoundClip yahooSound;
    private String username;
    private long startTime;
    private static final String highScoreFileName = "res/highscore.txt";
    private boolean isSoundtrackPlaying;

    public long getBestTimeSeconds() {
        return bestTime/1000000000;
    }

    public String getBestName() {
        return bestName;
    }

    public long getStartTime() {
        return startTime;
    }

    private long bestTime;
    private String bestName;

    public Game() {
        System.out.println(System.nanoTime());
        levels = new GameLevel[noOfLevels];
        gameJFrame = new JFrame("Bad Dead Redemption");
        mainMenuJFrame = new JFrame("Bad Dead Redemption - Main Menu");

        try {
            soundtrack = new SoundClip("res/sounds/theme_song.wav");
            yahooSound = new SoundClip("res/sounds/yahoo.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        mainMenu();
    }

    public void playNewGame() {
        fetchBestTime();
        startTime = System.nanoTime();

        mainMenuJFrame.setVisible(false);
        gameJFrame = new JFrame("Bad Dead Redemption");

        levelIndex = 0;
        levels[0] = new Level1();
        levels[1] = new Level2();
        levels[2] = new Level3();

        gameLevel = levels[levelIndex];
        gameLevel.populate();

        view = new OverlayView(gameLevel, this, 1200, 900);

        view.addMouseListener(new Shot(view, this));
        ((OverlayView) view).showHighScore();

        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameJFrame.setLocationByPlatform(true);
        gameJFrame.setResizable(false);
        gameJFrame.add(view);

        InGameMenu menu = new InGameMenu(this);
        gameJFrame.add(menu.getMainPanel(), BorderLayout.SOUTH);

        gameJFrame.pack();
        gameJFrame.setVisible(true);

        gameLevel.start();

        playSoundtrack();
    }

    public void playLevel(int levelToPlay) {
        mainMenuJFrame.setVisible(false);
        gameJFrame = new JFrame("Bad Dead Redemption");

        levels = new GameLevel[noOfLevels];
        levelIndex = levelToPlay-1;
        switch (levelToPlay) {
            case 1:
                levels[levelIndex] = new Level1();
                break;
            case 2:
                levels[levelIndex] = new Level2();
                break;
            case 3:
                levels[levelIndex] = new Level3();
                break;
        }

        gameLevel = levels[levelIndex];
        gameLevel.populate();

        view = new OverlayView(gameLevel, this, 1200, 900);
        ((OverlayView) view).setBackgroundImage(levelToPlay);

        view.addMouseListener(new Shot(view, this));

        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameJFrame.setLocationByPlatform(true);
        gameJFrame.setResizable(false);
        gameJFrame.add(view);

        InGameMenu menu = new InGameMenu(this);
        gameJFrame.add(menu.getMainPanel(), BorderLayout.SOUTH);

        gameJFrame.pack();
        gameJFrame.setVisible(true);

        gameLevel.start();

        playSoundtrack();
    }

    public static void main(String[] args) {
        new Game();
    }

    public GameLevel currentLevel() {
        return gameLevel;
    }

    public void nextLevel() {
        int cowboyBulletsLeft = currentLevel().getCowboy().getBullets() + 2;
        int cowboyLivesLeft = currentLevel().getCowboy().getLivesLeft();

        System.out.println(currentLevel().getCowboy().getBullets());
        gameLevel.stop();

        // For some reason the mouse listener from the first level would remain active
        // and still count the bullets from Level1's cowboy
        view.removeMouseListener(view.getMouseListeners()[0]);
        levelIndex++;
        if (levelIndex < levels.length) {
            if (levels[levelIndex] == null) {
                mainMenu();
            } else {
                System.out.println("Level passed! Next level is: LEVEL " + (levelIndex +1));
                gameLevel = levels[levelIndex];
                gameLevel.populate();

                gameLevel.getCowboy().setBullets(cowboyBulletsLeft);
                gameLevel.getCowboy().setLivesLeft(cowboyLivesLeft);

                ((OverlayView) view).setBackgroundImage(levelIndex+1);
                view.setWorld(gameLevel);
                view.addMouseListener(new Shot(view, this));
                gameLevel.start();
            }
        } else {
            gameWon();
        }
    }


    public void restartLevel() {
        gameLevel.stop();

        if (gameLevel instanceof Level1) {
            gameLevel = new Level1();
        } else if (gameLevel instanceof Level2) {
            gameLevel = new Level2();
        } else if (gameLevel instanceof Level3) {
            gameLevel = new Level3();
        }
        view.removeMouseListener(view.getMouseListeners()[0]);

        gameLevel.populate();
        view.setWorld(gameLevel);
        view.addMouseListener(new Shot(view, this));
        gameLevel.start();
    }

    public int getLevelIndex() {
        return levelIndex +1;
    }

    public void mainMenu() {
        gameJFrame.setVisible(false);
        try {
            gameLevel.stop();
            stopSoundtrack();
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        mainMenuJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuJFrame.setLocationByPlatform(true);
        mainMenuJFrame.setResizable(false);
        // size the game window to fit the gameLevel view

        MainMenu mainMenu = new MainMenu(this);
        mainMenuJFrame.add(mainMenu.getMainPanel());

        mainMenuJFrame.pack();
        mainMenuJFrame.setVisible(true);
    }

    public void gameWon() {
        updateBestTime();

        yahooSound.play();
        System.out.println("Game won");
        mainMenu();
    }

    public void levelFailed() {
        System.out.println("Level failed");
        gameLevel.stop();
        mainMenu();
    }

    public void quit() {
        System.exit(0);
    }

    public void setUsername(String text) {
        username = text;
    }
    public String getUsername() {
        return username;
    }


    private void fetchBestTime() {
        try (BufferedReader reader = new BufferedReader(new FileReader(highScoreFileName))){
            System.out.println("Reading " + highScoreFileName + " ...");
            String line = reader.readLine();
            // file is assumed to contain one name, score pair per line
            String[] tokens = line.split(",");
            String name = tokens[0];
            long recordTime = Long.parseLong(tokens[1]);
            System.out.println("Name: " + name + ", Score: " + recordTime);

            bestTime = recordTime;
            bestName = name;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateBestTime() {
        long currentGameTime = System.nanoTime() - startTime;
        if (currentGameTime < bestTime) {
            try (FileWriter fw = new FileWriter(highScoreFileName)) {
                fw.write(username + "," + currentGameTime);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void toggleSoundtrack() {
        if (isSoundtrackPlaying) {
            stopSoundtrack();
        } else {
            playSoundtrack();
        }
    }

    private void playSoundtrack() {
        soundtrack.loop();
        isSoundtrackPlaying = true;
    }

    private void stopSoundtrack() {
        soundtrack.stop();
        isSoundtrackPlaying = false;
    }
}


