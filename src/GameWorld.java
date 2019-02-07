import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    public GameWorld() {
        super();

        Shape shape = new BoxShape(22, 0.5f);
        Body ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0, -11.5f));

        // platform1
        Shape platform1Shape = new BoxShape(8, 0.5f);
        Body platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-9, 5.5f));

        //bird
        /* Shape birdShape = new PolygonShape(
                0.149f,0.975f, 0.775f,0.193f, 0.772f,-0.099f, 0.401f,-0.928f, -0.36f,-0.922f, -0.719f,-0.025f, -0.725f,0.163f, -0.14f,0.972f);
        Body bird = new DynamicBody(world, birdShape);
        bird.addImage(new BodyImage("res/yellow-bird.gif", 2.25f));
        bird.setPosition(new Vec2(8, -10));
        */

        Cowboy cowboy = new Cowboy(this);
        cowboy.setPosition(new Vec2(-10,-10));
    }
}
