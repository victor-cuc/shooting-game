import city.cs.engine.*;

import javax.swing.*;
import java.awt.*;

public class Game {
    private GameLevel gameLevel;
    private OverlayView view;
    private GameLevel[] levels = new GameLevel[2];
    private int levelNo;

    public Game() {
        levelNo = 0;
        levels[0] = new Level1();
        levels[1] = new Level2();

        gameLevel = levels[levelNo];
        gameLevel.populate();

        view = new OverlayView(gameLevel, this, 1200, 900);

        view.addMouseListener(new Shot(view, this));

        final JFrame window = new JFrame("Bad Dead Redemption");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.add(view);
        window.setResizable(false);
        // size the game window to fit the gameLevel view

        InGameMenu menu = new InGameMenu(this);
        window.add(menu.getMainPanel(), BorderLayout.SOUTH);

        window.pack();
        window.setVisible(true);

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
            System.out.println("Game won");
            System.exit(0);
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
}


