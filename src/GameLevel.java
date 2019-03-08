import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public abstract class GameLevel extends World {
    private Cowboy cowboy;

    public void populate() {
        cowboy = new Cowboy(this);
        cowboy.setPosition(startPosition());
    }

    /** The initial position of the player. */
    public abstract Vec2 startPosition();

    /** Is this level complete? */
    public abstract boolean isCompleted();

    public abstract void enemyHit();

    public Cowboy getCowboy() {
        return cowboy;
    }
}
