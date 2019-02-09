import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

public class SpriteWalker implements StepListener {
    private Walker walker;
    private float speed;
    private Vec2 startPoint;
    private Vec2 endPoint;

    public SpriteWalker(Walker walker, float speed, float distance) {
        this.walker = walker;
        this.speed = speed;
        this.startPoint = walker.getPosition();
        endPoint = startPoint.add(new Vec2(startPoint.x + distance, startPoint.y));
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (walker.getPosition().x <= startPoint.x) {
            walker.startWalking(speed);
        }

        else if (walker.getPosition().x >= endPoint.x) {
            walker.startWalking(-speed);
        }
    }
}
