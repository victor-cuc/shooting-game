import city.cs.engine.*;

public class BulletHit implements CollisionListener {
//
//    private Game game;
//
//    public BulletHit(Game game) {
//        this.game = game;
//    }
    private Bullet bullet;

    @Override
    public void collide(CollisionEvent collisionEvent) {
        bullet = (Bullet) collisionEvent.getReportingBody();
        Body otherBody = collisionEvent.getOtherBody();
        GameLevel gameLevel = (GameLevel) collisionEvent.getReportingBody().getWorld();

        if ( ! (otherBody instanceof Bullet)) {

            bullet.incCollisionNo();
            System.out.println("Bullet collision " + bullet.getCollisionNo());

            if (otherBody instanceof DynamicBody) {
                System.out.println("Bullet hit " + collisionEvent.getOtherBody().toString());
                bullet.destroy();
                if (otherBody instanceof Enemy) {
                    otherBody.destroy();
                    gameLevel.enemyHit();
                    if (gameLevel.isCompleted()) {
                        gameLevel.stop();
                        System.exit(0);
                    }
                }

                else if (otherBody instanceof Cowboy) {

                    if (((Cowboy) otherBody).getLivesLeft() <= 1) {
                        otherBody.destroy();
                        System.out.println("You killed yourself");
                    } else {
                        ((Cowboy) otherBody).decLivesLeft();
                        System.out.println("Oh no, you hit the cowboy. You've got " + ((Cowboy) otherBody).getLivesLeft() + " lives left");
                    }
                }
            }

            if (bullet.getCollisionNo() >= 3) {
                bullet.destroy();
                System.out.println("Bullet destroyed on collision no: " + bullet.getCollisionNo());
            }
        }


    }
}
