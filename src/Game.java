import city.cs.engine.*;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class Game {
    private GameLevel gameLevel;
    private UserView view;
    private GameLevel[] levels = new GameLevel[2];
    private int levelNo;
    private JFrame gameJFrame;
    private JFrame mainMenuJFrame;

    public Game() {
        gameJFrame = new JFrame("Bad Dead Redemption");
        mainMenuJFrame = new JFrame("Bad Dead Redemption - Main Menu");

        mainMenu();
    }

    public void playGame() {
        mainMenuJFrame.setVisible(false);
        gameJFrame = new JFrame("Bad Dead Redemption");

        levelNo = 0;
        levels[0] = new Level1();
        levels[1] = new Level2();

        gameLevel = levels[levelNo];
        gameLevel.populate();

        view = new OverlayView(gameLevel, this, 1200, 900);

        view.addMouseListener(new Shot(view, this));

        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameJFrame.setLocationByPlatform(true);
        gameJFrame.setResizable(false);
        gameJFrame.add(view);

        InGameMenu menu = new InGameMenu(this);
        gameJFrame.add(menu.getMainPanel(), BorderLayout.SOUTH);

//        MainMenu mainMenu = new MainMenu(this);
//        window.add(mainMenu.getMainPanel());

        gameJFrame.pack();
        gameJFrame.setVisible(true);

        gameLevel.start();
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
        levelNo++;
        if (levelNo < levels.length) {
            System.out.println("Level passed! Next level is: LEVEL " + (levelNo+1));
            gameLevel = levels[levelNo];
            gameLevel.populate();

            gameLevel.getCowboy().setBullets(cowboyBulletsLeft);
            gameLevel.getCowboy().setLivesLeft(cowboyLivesLeft);

            view.setWorld(gameLevel);
            view.addMouseListener(new Shot(view, this));
            gameLevel.start();
        } else {
            gameWon();
        }
    }

    //TODO Add more levels to if statement
    public void restartLevel() {
        gameLevel.stop();

        if (gameLevel instanceof Level1) {
            gameLevel = new Level1();
        } else if (gameLevel instanceof Level2) {
            gameLevel = new Level2();
        }
        view.removeMouseListener(view.getMouseListeners()[0]);

        gameLevel.populate();
        view.setWorld(gameLevel);
        view.addMouseListener(new Shot(view, this));
        gameLevel.start();
    }

    public int getLevelNo() {
        return levelNo +1;
    }

    public void mainMenu() {
        gameJFrame.setVisible(false);
        try {
            gameLevel.stop();
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
}


