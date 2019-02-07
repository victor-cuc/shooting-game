import city.cs.engine.*;

import javax.swing.*;

public class Game {
    private World world;
    private UserView view;

    public Game() {
        world = new GameWorld();

        view = new UserView(world, 1200, 900);

        view.addMouseListener(new Shot(view));

        final JFrame frame = new JFrame("Bad Dead Redemption");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        frame.setVisible(true);

        world.start();
    }

    public static void main(String[] args) {
        new Game();
    }
}


