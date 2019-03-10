import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

public class SpriteWalker implements StepListener {
    private Walker walker;
    private float speed;
    private float startPointX;
    private float endPointX;

    public SpriteWalker(Walker walker, float speed, float distance) {
        this.walker = walker;
        this.speed = speed;
        this.startPointX = walker.getPosition().x;
        endPointX = startPointX + distance;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (walker.getPosition().x <= startPointX) {
            walker.startWalking(speed);
            if (! walker.getImages().get(0).isFlippedHorizontal()) {
                walker.getImages().get(0).flipHorizontal();
            }
        }

        else if (walker.getPosition().x >= endPointX) {
            walker.startWalking(-speed);
            if (walker.getImages().get(0).isFlippedHorizontal()) {
                walker.getImages().get(0).flipHorizontal();
            }
        }
    }
}
