import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickHandler extends MouseAdapter {
/*

    private static final float RADIUS = 0.5f;

    private static final Shape ballShape = new CircleShape(RADIUS);

    private static final BodyImage ballImage = new BodyImage("res/bowl.png", 2*RADIUS);

    private WorldView view;

    public MouseClickHandler(WorldView view) {
        this.view = view;
    }

    public void mousePressed(MouseEvent e) {
        DynamicBody ball = new DynamicBody(view.getWorld(), ballShape);
        ball.setPosition(view.viewToWorld(e.getPoint()));
        ball.addImage(ballImage);
    }
*/
    //private Vec2 startPosition = new Vec2();

    private WorldView view;

    public MouseClickHandler(WorldView view) {
        this.view = view;
    }

    public void mousePressed(MouseEvent e) {
        Bullet bullet = new Bullet(view.getWorld());
        bullet.setPosition(view.viewToWorld(e.getPoint()));
        bullet.applyForce(new Vec2(100, 300));
    }

}
