import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

public class BulletTracker implements StepListener {
    private Bullet bullet;
    float angle;

    public BulletTracker(Bullet bullet) {
        this.bullet = bullet;
    }


    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        //System.out.println("lin vel:" +bullet.getLinearVelocity());
        if (bullet.getLinearVelocity().x < 0) {
            angle = (float) (Math.atan(bullet.getLinearVelocity().y/bullet.getLinearVelocity().x) + Math.PI/2);
        } else {
            angle = (float) (Math.atan(bullet.getLinearVelocity().y/bullet.getLinearVelocity().x) - Math.PI/2);
        }
        bullet.setAngle(angle);
    }
}
