import city.cs.engine.Walker;
import city.cs.engine.World;

/**
 * Base class for enemy entities in the game
 * */
public class Enemy extends Walker {

    /**
     * @param world
     * This should be the current world of the game
     * */
    public Enemy(World world) {
        super(world);
    }

    /**
     * When called makes the enemy walk back and forth over a certain distance with a certain speed
     * @param speed The speed the enemy will walk around
     * @param distance The distance to the right that the enemy will walk until turning around
     * */
    public void walkAround(float speed, float distance) {
        this.getWorld().addStepListener(new SpriteWalker(this, speed, distance));
    }
}
