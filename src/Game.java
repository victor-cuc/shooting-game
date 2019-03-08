import city.cs.engine.*;

import javax.swing.*;

public class Game {
    private GameLevel gameLevel;
    private UserView view;
    private Cowboy cowboy;

    public Game() {
        gameLevel = new Level1();
        cowboy = new Cowboy(gameLevel);
        gameLevel.populate(cowboy);

        view = new UserView(gameLevel, 1200, 900);

        view.addMouseListener(new Shot(view));

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
}


