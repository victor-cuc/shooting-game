import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OverlayView extends UserView {
    private Game game;
    private BufferedImage heartImage;
    private BufferedImage bulletImage;
    private BufferedImage backgroundImage;

    public OverlayView(World w, Game game, int width, int height) {
        super(w, width, height);
        this.game = game;

        try {
            backgroundImage = ImageIO.read(new File("res/desert.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bulletImage = ImageIO.read(new File("res/bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            heartImage = ImageIO.read(new File("res/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        int bulletsLeft = ((GameLevel) super.getWorld()).getCowboy().getBullets();
        String levelNo = Integer.toString(game.getLevelIndex());

        g.drawString("Level " + levelNo, 20, 20);
//        g.drawString(("Bullets Left: " + bulletsLeft), 10, 40);


        if (bulletsLeft > 0) {
            int imageXCoord = 10;
            for (int i = 0; i < bulletsLeft; i++) {
                try {
                    g.drawImage(bulletImage, imageXCoord, 30, 30, 30, null, null);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                imageXCoord += 30;
            }
        } else {
            g.drawString("Out of Ammo", 20, 50);
        }
        int imageXCoord = 15;
        for (int i = 0; i < game.currentLevel().getCowboy().getLivesLeft(); i++) {
            try {
                g.drawImage(heartImage, imageXCoord, 70, 25, 25, null, null);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            imageXCoord += 30;
        }
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        try {
            g.drawImage(backgroundImage, 0, 0, 1200, 900, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
