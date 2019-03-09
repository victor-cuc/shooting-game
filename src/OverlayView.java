import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

public class OverlayView extends UserView {
    private Game game;

    public OverlayView(World w, Game game, int width, int height) {
        super(w, width, height);
        this.game = game;
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        int bulletsLeft = ((GameLevel) super.getWorld()).getCowboy().getBullets();
        String levelNo = Integer.toString(game.getLevelNo());

        g.drawString("Level " + levelNo, 20, 20);
//        g.drawString(("Bullets Left: " + bulletsLeft), 10, 40);

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("res/bullet.png"));

            if (bulletsLeft > 0) {
                int imageXCoord = 10;
                for (int i = 0; i < bulletsLeft; i++) {
                    g.drawImage(bufferedImage, imageXCoord, 30, 30, 30, null, null);
                    imageXCoord += 30;
                }
            } else {
                g.drawString("Out of Ammo", 20, 50);
            }
        } catch (IOException e) {
            e.printStackTrace();

            g.drawString("Image not found", 20, 50);
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("res/heart.png"));
            int imageXCoord = 15;
            for (int i = 0; i < game.currentLevel().getCowboy().getLivesLeft(); i++) {
                g.drawImage(bufferedImage, imageXCoord, 70, 25, 25, null, null);
                imageXCoord += 30;
            }
            } catch (IOException e) {
                e.printStackTrace();

                g.drawString("Image not found", 20, 70);
            }


    }
}
