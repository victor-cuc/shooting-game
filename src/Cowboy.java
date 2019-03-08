import city.cs.engine.*;

public class Cowboy extends DynamicBody {
    // -0.03f,0.476f, 0.324f,-0.126f, 0.106f,-0.472f, -0.168f,-0.474f, -0.262f,-0.136f, -0.306f,0.45f
    private int bullets;
    private int livesLeft;

    public Cowboy(World w) {
        super(w);

        bullets = 3;
        livesLeft = 3;

        PolygonShape cowboyShape = new PolygonShape(
                -0.49f,3.92f, 2.2f,-0.85f, 0.93f,-3.78f, -1.37f,-3.73f, -2.04f,-1.14f, -2.3f,3.6f);

        SolidFixture fixture = new SolidFixture(this, cowboyShape);
        this.addImage(new BodyImage("res/yosemite_sam.png", 8));
    }

    public int getBullets() {
        return bullets;
    }

    public void decrementBullets() {
        bullets--;
        System.out.println(bullets + " bullets left");
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void decLivesLeft() {
        livesLeft--;
    }

    public void addBullets(int i) {
        bullets += i;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }
}
