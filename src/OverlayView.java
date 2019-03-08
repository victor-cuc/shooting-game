import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;

public class OverlayView extends UserView {

    public OverlayView(World w, int width, int height) {
        super(w, width, height);
    }

    protected void paintForeground(Graphics2D g) {
        String bulletsLeft = Integer.toString(((GameLevel) super.getWorld()).getCowboy().getBullets());
        g.drawString(("Bullets Left: " + bulletsLeft), 10, 20);
    }
}
