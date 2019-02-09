import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Bandit extends Enemy {
    public Bandit(World world) {
        super(world);

        PolygonShape banditShape = new PolygonShape(
                0.01f,2.87f, 2.64f,-0.94f, 2.42f,-2.95f, -2.56f,-2.96f, -2.96f,-1.6f, -1.88f,2.58f);

        SolidFixture fixture = new SolidFixture(this, banditShape);
        this.addImage(new BodyImage("res/taz.gif", 6));
    }
}
