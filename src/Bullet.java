import city.cs.engine.*;

public class Bullet extends DynamicBody implements CollisionListener {

    private int collisionNo = 0;

    public Bullet(World w) {
        super(w);

        CircleShape bulletShape = new CircleShape(0.1f);

        SolidFixture fixture = new SolidFixture(this, bulletShape, 10);
        fixture.setRestitution(1);
        fixture.setFriction(10000000);
        this.addImage(new BodyImage("res/bullet.png"));

        this.setGravityScale(0);
        this.setBullet(true);
        this.addCollisionListener(this::collide);

    }

    public void collide(CollisionEvent e) {
        System.out.println(collisionNo);

        //number of collisions until disappears
        if (collisionNo < 3) {
            collisionNo++;
        } else {
            this.destroy();
        }
    }
}
