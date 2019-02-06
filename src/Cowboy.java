import city.cs.engine.*;

public class Cowboy extends DynamicBody {
    // -0.03f,0.476f, 0.324f,-0.126f, 0.106f,-0.472f, -0.168f,-0.474f, -0.262f,-0.136f, -0.306f,0.45f
    public Cowboy(World w) {
        super(w);

        PolygonShape cowboyShape = new PolygonShape(
                -0.49f,3.92f, 2.2f,-0.85f, 0.93f,-3.78f, -1.37f,-3.73f, -2.04f,-1.14f, -2.3f,3.6f);

        SolidFixture fixture = new SolidFixture(this, cowboyShape);
        this.addImage(new BodyImage("res/yosemite_sam.png", 8));
    }

}
