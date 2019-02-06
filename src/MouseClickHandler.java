import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickHandler extends MouseAdapter {

    private WorldView view;

    public MouseClickHandler(WorldView view) {
        this.view = view;
    }

    public void mousePressed(MouseEvent e) {
        Vec2 startPosition = new Vec2(-7.35f, -7.95f);

        Bullet bullet = new Bullet(view.getWorld());
        bullet.setPosition(startPosition);
        bullet.applyForce(view.viewToWorld(e.getPoint()).sub(startPosition).mul(20));

        System.out.println(view.viewToWorld(e.getPoint()));
    }

}
