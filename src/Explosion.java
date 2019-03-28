import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Explosion implements CollisionListener, StepListener{
    private Body reportingBody;
    private boolean hit;
    private float explosionTime;
    private SoundClip explosionSound;
    public static final float EXPLOSION_LENGTH = 0.7f;

    public Explosion() {
        hit = false;
        explosionTime = 0;
        try {
            explosionSound = new SoundClip("res/sounds/explosion.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        reportingBody = collisionEvent.getReportingBody();
        if (collisionEvent.getOtherBody() instanceof Bullet) {
            explosionSound.play();
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
