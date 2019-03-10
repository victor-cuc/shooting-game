import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

public class Level3 extends GameLevel {
    public static final int NUM_ENEMIES = 1;
    private int enemiesKilled;

    @Override
    public void populate() {
        super.populate();

        enemiesKilled = 0;

        Bandit bandit;
        ArrayList<Barrel> barrels = new ArrayList<>();

        Shape shape = new BoxShape(22, 0.5f);
        Body ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0, -11.5f));

        // platform1
        Shape platform1Shape = new BoxShape(8, 0.5f);
        Body platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-9, 5.5f));

        bandit = new Bandit(this);
        bandit.setPosition(new Vec2(0, -10));
        bandit.walkAround(3, 20);

        bandit = new Bandit(this);
        bandit.setPosition(new Vec2(0, -10));
        bandit.walkAround(3, 20);

        barrels.add(new Barrel(this));
        barrels.get(0).setPosition(new Vec2(-20, -10));

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-10,-10);
    }

    @Override
    public boolean isCompleted() {
        return (enemiesKilled == NUM_ENEMIES);
    }

    @Override
    public void enemyHit() {
        enemiesKilled++;
    }
}
