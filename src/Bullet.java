import city.cs.engine.*;

public class Bullet extends DynamicBody {

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
        this.addCollisionListener(new BulletHit());

    }

    public int getCollisionNo() {
        return collisionNo;
    }

    public void incCollisionNo() {
        collisionNo++;
    }
}
