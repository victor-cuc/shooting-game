import city.cs.engine.*;

public class Explosion implements CollisionListener, StepListener {

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Bullet) {
            collisionEvent.getReportingBody().addImage(new BodyImage("res/explosion.gif", 6));
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
