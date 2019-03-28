import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Shot extends MouseAdapter {

    private final Game game;
    private WorldView view;
    private Cowboy cowboy;
    private SoundClip shotSound;
    private SoundClip dryShotSound;
    private boolean soundsWorking = true;

    public Shot(WorldView view, Game game) {
        this.view = view;
        this.game = game;
        cowboy = ((GameLevel) view.getWorld()).getCowboy();

        try {
            shotSound = new SoundClip("res/sounds/gun_fire.wav");
            dryShotSound = new SoundClip("res/sounds/dry_fire.wav");
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
            soundsWorking = false;
        }
    }

    public void mousePressed(MouseEvent e) {
        if (cowboy.getBullets() > 0) {

            Vec2 startPosition;
            Vec2 clickPosition = view.viewToWorld(e.getPoint());

            // Shoot from left or right
            if (clickPosition.x < cowboy.getPosition().x) {
                startPosition = new Vec2((float) (cowboy.getPosition().x - 2.5), cowboy.getPosition().y);
            } else {
                startPosition = new Vec2((float) (cowboy.getPosition().x + 2.2), (float) (cowboy.getPosition().y - 0.5));
            }
            Vec2 shootingVector = clickPosition.sub(startPosition);

            Bullet bullet = new Bullet(view.getWorld(), game);
            bullet.setPosition(startPosition);
            view.getWorld().addStepListener(new BulletTracker(bullet));
            bullet.applyForce(shootingVector.mul(500 / shootingVector.length()));

            shotSound.play();

            System.out.println(view.viewToWorld(e.getPoint()));

            cowboy.decrementBullets();

        } else {
            dryShotSound.play();
            System.out.println("Out of ammo!");

        }
    }

}
