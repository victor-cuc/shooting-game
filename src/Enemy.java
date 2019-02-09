import city.cs.engine.Walker;
import city.cs.engine.World;

public class Enemy extends Walker {
    //TODO: private int lives;

    public Enemy(World world) {
        super(world);
    }

    public void walkAround(float speed, float distance) {
        getWorld().addStepListener(new SpriteWalker(this, speed, distance));
    }
}
