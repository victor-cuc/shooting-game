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

            Vec2 startPosition = new Vec2(-7.35f, -7.95f);
            Vec2 clickPosition = view.viewToWorld(e.getPoint());
            Vec2 shootingVector = clickPosition.sub(startPosition);
            float angle = (float) Math.atan(shootingVector.y / shootingVector.x);

            Bullet bullet = new Bullet(view.getWorld());
            bullet.setPosition(startPosition);
            view.getWorld().addStepListener(new BulletTracker(bullet));
            bullet.applyForce(shootingVector.mul(300 / shootingVector.length()));

            System.out.println(view.viewToWorld(e.getPoint()));

            //cowboy.decrementBullets();
        }
    }

}
