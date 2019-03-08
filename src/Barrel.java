import city.cs.engine.*;

public class Barrel extends DynamicBody {
    public Barrel(World w) {
        super(w);

        PolygonShape barrelShape = new PolygonShape(
                -0.6f,2.95f, -2.34f,2.72f, -2.12f,-2.89f, 2.21f,-2.96f, 2.28f,2.82f, 1.2f,2.96f);

        SolidFixture fixture = new SolidFixture(this, barrelShape);
        this.addImage(new BodyImage("res/barrel.png", 6));

        Explosion explosion = new Explosion();
        this.addCollisionListener(explosion);
        w.addStepListener(explosion);
        this.addDestructionListener(destructionEvent -> {
                ((GameLevel) w).getCowboy().addBullets(3);
                System.out.println("Extra bullets! Now you have " + ((GameLevel) w).getCowboy().getBullets());
            });

    }
}
