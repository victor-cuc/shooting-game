import city.cs.engine.*;

public class BulletHit implements CollisionListener {

    private Bullet bullet;

    @Override
    public void collide(CollisionEvent collisionEvent) {
        bullet = (Bullet) collisionEvent.getReportingBody();
        Body otherBody = collisionEvent.getOtherBody();

        if ( ! (otherBody instanceof Bullet)) {

            bullet.incCollisionNo();
            System.out.println("Bullet collision " + bullet.getCollisionNo());

            if (otherBody instanceof Enemy) {
                System.out.println("Bullet hit " + collisionEvent.getOtherBody().toString());
                bullet.destroy();
                otherBody.destroy();

            } else if (otherBody instanceof Cowboy) {
                bullet.destroy();

                if (((Cowboy) otherBody).getLivesLeft() <= 1) {
                    otherBody.destroy();
                    System.out.println("You killed yourself");
                } else {
                    ((Cowboy) otherBody).decLivesLeft();
                    System.out.println("Oh no, you hit the cowboy. You've got " + ((Cowboy) otherBody).getLivesLeft() + " lives left");
                }

            } else if (bullet.getCollisionNo() >= 3) {
                bullet.destroy();
                System.out.println("Bullet destroyed on collision no: " + bullet.getCollisionNo());
            }
        }


    }
}
