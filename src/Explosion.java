import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Explosion implements CollisionListener, StepListener{
    private Body reportingBody;
    private boolean hit;
    private float explosionTime;
    public static final float EXPLOSION_LENGTH = 0.7f;


    public Explosion() {
        hit = false;
        explosionTime = 0;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        reportingBody = collisionEvent.getReportingBody();
        if (collisionEvent.getOtherBody() instanceof Bullet) {
            //reportingBody.addImage(new BodyImage("res/explosion.gif", 6));
            reportingBody.removeAllImages();
            new AttachedImage(reportingBody, new BodyImage("res/explosion.gif"), 6, 0, new Vec2(0, 0));
            hit = true;
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (hit) {
            explosionTime += stepEvent.getStep();

            if (explosionTime >= EXPLOSION_LENGTH) {
                reportingBody.destroy();
            }
        }
    }
}
