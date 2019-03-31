import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

public class Level2 extends GameLevel {
    public static final int NUM_ENEMIES = 3;
    private int enemiesKilled;

    @Override
    public void populate() {
        super.populate();

        enemiesKilled = 0;


        Shape shape = new BoxShape(29, 0.5f);
        Body ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0, -21.5f));

        // platform1
        Shape platform1Shape = new BoxShape(8, 0.5f);
        Body platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(9, 5.5f));

        // platform2
        Shape platform2Shape = new BoxShape(8, 0.5f);
        Body platform2 = new StaticBody(this, platform2Shape);
        platform2.setPosition(new Vec2(-20, 2.5f));

        //platform 3
        Shape platform3Shape = new BoxShape(8, 0.5f);
        Body platform3 = new StaticBody(this, platform3Shape);
        platform3.setPosition(new Vec2(4, 14));

        // platform4
        Shape platform4Shape = new BoxShape(8, 0.5f);
        Body platform4 = new StaticBody(this, platform4Shape);
        platform4.setPosition(new Vec2(-20, -5.5f));
        platform4.rotate((float)Math.PI/4);

        // platform5
        Shape platform5Shape = new BoxShape(8, 0.5f);
        Body platform5 = new StaticBody(this, platform5Shape);
        platform5.setPosition(new Vec2(5, -5.5f));
        platform5.rotate((float)-Math.PI/4);

        //platform 6
        Shape platform6Shape = new BoxShape(0.5f, 6);
        Body platform6 = new StaticBody(this, platform6Shape);
        platform6.setPosition(new Vec2(-4, -15.5f));

        //platform 7
        Shape platform7Shape = new BoxShape(0.2f, 3);
        Body platform7 = new StaticBody(this, platform7Shape);
        platform7.setPosition(new Vec2(-12, 8.5f));



        Bandit bandit1 = new Bandit(this);
        bandit1.setPosition(new Vec2(3.5f, 6.5f));
        bandit1.walkAround(8, 8);

        Bandit bandit2 = new Bandit(this);
        bandit2.setPosition(new Vec2(-20, -18f));
        bandit2.walkAround(2, 9);

        Bandit bandit3 = new Bandit(this);
        bandit3.setPosition(new Vec2(-24, 6));
        bandit3.walkAround(4, 9);



        Barrel barrel = new Barrel(this);
        barrel.setPosition(new Vec2(-1, 17));

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(10,-18);
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
