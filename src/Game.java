import city.cs.engine.*;

import javax.swing.*;

public class Game {
    private GameLevel gameLevel;
    private UserView view;
    private GameLevel[] levels = new GameLevel[2];
    private int levelNo;

    public Game() {
        levelNo = 0;
        levels[0] = new Level1();
        levels[1] = new Level2();

        gameLevel = levels[levelNo];
        gameLevel.populate();

        view = new UserView(gameLevel, 1200, 900);

        view.addMouseListener(new Shot(view, this));

        final JFrame frame = new JFrame("Bad Dead Redemption");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(false);
        // size the game window to fit the gameLevel view
        frame.pack();
        frame.setVisible(true);

        gameLevel.start();
    }

    public static void main(String[] args) {
        new Game();
    }

    public GameLevel currentLevel() {
        return gameLevel;
    }

    public void levelFailed() {
        System.out.println("Level failed");
        gameLevel.stop();
        System.exit(0);
    }

    public void nextLevel() {
        int cowboyBulletsLeft = currentLevel().getCowboy().getBullets() + 2;
        int cowboyLivesLeft = currentLevel().getCowboy().getLivesLeft();

        System.out.println(currentLevel().getCowboy().getBullets());
        gameLevel.stop();
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
            System.out.println("Game won");
            System.exit(0);
        }
    }
}


