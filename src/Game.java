import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Game {
    private World world;
    private UserView view;

    public Game() {
        world = new World();

        // ground
        Shape shape = new BoxShape(11, 0.5f);
        Body ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0, -11.5f));

        // platform1
        Shape platform1Shape = new BoxShape(4, 0.5f);
        Body platform1 = new StaticBody(world, platform1Shape);
        platform1.setPosition(new Vec2(-9, 5.5f));

        //bird
        Shape birdShape = new PolygonShape(
                0.149f,0.975f, 0.775f,0.193f, 0.772f,-0.099f, 0.401f,-0.928f, -0.36f,-0.922f, -0.719f,-0.025f, -0.725f,0.163f, -0.14f,0.972f);
        Body bird = new DynamicBody(world, birdShape);
        bird.addImage(new BodyImage("res/yellow-bird.gif", 2.25f));
        bird.setPosition(new Vec2(8, -10));

        view = new UserView(world, 500, 500);

        view.addMouseListener(new MouseClickHandler(view));

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


