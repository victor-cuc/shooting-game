import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class BulletHit implements CollisionListener {
    private final Bullet bullet;

    public BulletHit(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {

        if ( ! (collisionEvent.getOtherBody() instanceof Bullet)) {

            if (bullet.getCollisionNo() < 3 && collisionEvent.getOtherBody() instanceof StaticBody) {
                bullet.incCollisionNo();
                //this.setAngle();  Set bullet towards direction after bounce
            } else {
                bullet.destroy();
                System.out.println("Bullet destroyed on collision no: " + bullet.getCollisionNo());
            }
        }

        System.out.println("Bullet collision " + bullet.getCollisionNo());
    }
}
