import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class BulletHit implements CollisionListener {

    private Game game;
    private SoundClip gruntSound;
    private SoundClip ricochetSound;
    private Bullet bullet;

    public BulletHit(Game game) {
        this.game = game;
        try {
            gruntSound = new SoundClip("res/sounds/male_grunt.wav");
            ricochetSound = new SoundClip("res/sounds/ricochet.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        bullet = (Bullet) collisionEvent.getReportingBody();
        Body otherBody = collisionEvent.getOtherBody();
        GameLevel gameLevel = game.currentLevel();

        if ( ! (otherBody instanceof Bullet)) {

            bullet.incCollisionNo();
            System.out.println("Bullet collision " + bullet.getCollisionNo());

            if (otherBody instanceof DynamicBody) {
                System.out.println("Bullet hit " + collisionEvent.getOtherBody().toString());
                bullet.destroy();
                if (otherBody instanceof Enemy) {
                    gruntSound.play();
                    gameLevel.enemyHit();
                    otherBody.destroy();

                } else if (otherBody instanceof Cowboy) {

                    if (((Cowboy) otherBody).getLivesLeft() <= 1) {
                        otherBody.destroy();
                        System.out.println("You killed yourself");
                        game.levelFailed();
                    } else {
                        ((Cowboy) otherBody).decLivesLeft();
                        System.out.println("Oh no, you hit the cowboy. You've got " + ((Cowboy) otherBody).getLivesLeft() + " lives left");
                    }
                }
            } else {
                ricochetSound.stop();
                ricochetSound.play();
            }

            if (bullet.getCollisionNo() >= 3) {
                bullet.destroy();
                System.out.println("Bullet destroyed on collision no: " + bullet.getCollisionNo());
            }

            if (gameLevel.isCompleted()) {
                game.nextLevel();
            }
        }

    }
}
