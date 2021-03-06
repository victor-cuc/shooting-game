import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Creates the overlay for the game
 * <p>Adds an overlay to the class displaying the following:</p>
 * <ul>
 *     <li>Current level</li>
 *     <li>Remaining bullets</li>
 *     <li>Remaining lives (Cowboy)</li>
 *     <li>If showHighScore() is called:
 *       <ul>
 *         <li>Best time</li>
 *         <li>Time elapsed</li>
 *         <li>Current username</li>
 *       </ul>
 *     </li>
 * </ul>
 * */

public class OverlayView extends UserView {
    private Game game;
    private BufferedImage heartImage;
    private BufferedImage bulletImage;
    private BufferedImage backgroundImage;
    private boolean isShowingHighscore;

    /**
     * Create a new OverlayView
     *
     * @param w
     *          The world of the game
     * @param game
     *             current Game
     * @param width
     *             width of the UserView
     *
     * @param height
     *              height of UserView
     * */
    public OverlayView(World w, Game game, int width, int height) {
        super(w, width, height);
        this.game = game;
        isShowingHighscore = false;

        try {
            backgroundImage = ImageIO.read(new File("res/level_1_background.png"));
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
        if (isShowingHighscore) {
            g.drawString("Playing as: " + game.getUsername(), 20, 160);
            g.drawString("Best time: " + game.getBestTimeSeconds() + " seconds - by " + game.getBestName(), 20, 120);
            g.drawString(runStopwatch(), 20, 140);
        }
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
            g.drawString("Out of Ammo", 20, 30);
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

    public void setBackgroundImage(int levelNo) {
        switch (levelNo) {
            case 1:
                try {
                    backgroundImage = ImageIO.read(new File("res/level_1_background.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    backgroundImage = ImageIO.read(new File("res/level_2_background.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    backgroundImage = ImageIO.read(new File("res/level_3_background.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * Has to be called in order to display username, time elapsed and highscore.
     *
     * Should not be called unless playing full game and highscores are being recorded.
     * */
    public void showHighScore() {
        isShowingHighscore = true;
    }

    /**
     * @return timeElapsed as a formatted String
     * */

    public String runStopwatch() {
        long timeElapsed = System.nanoTime() - game.getStartTime();
        long milliseconds = TimeUnit.MILLISECONDS.convert(timeElapsed, TimeUnit.NANOSECONDS);

        int minutes = (int) milliseconds / 60000;
        int seconds= (int) (milliseconds % 60000)/1000;
        int millisecondsDisplayed = (int) milliseconds % 1000 / 10;

        return String.format("%02d : %02d : %02d\n", minutes, seconds, millisecondsDisplayed);
    }
}
