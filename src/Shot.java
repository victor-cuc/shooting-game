import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shot extends MouseAdapter {

    private WorldView view;
    private Cowboy cowboy;

    public Shot(WorldView view) {
        this.view = view;
        cowboy = ((GameWorld) view.getWorld()).getCowboy();
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

            Bullet bullet = new Bullet(view.getWorld());
            bullet.setPosition(startPosition);
            view.getWorld().addStepListener(new BulletTracker(bullet));
            bullet.applyForce(shootingVector.mul(300 / shootingVector.length()));

            System.out.println(view.viewToWorld(e.getPoint()));

            cowboy.decrementBullets();
        } else {
            System.out.println("Out of ammo!");
        }
    }

}
