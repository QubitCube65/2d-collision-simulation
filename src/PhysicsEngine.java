import java.util.List;

public class PhysicsEngine {
    private List<Ball> balls;

    public PhysicsEngine(List<Ball> balls) {
        this.balls = balls;
    }
    public void update(double dt) {
        for(Ball b : balls) {
            b.update(dt);
            wallCollision(b);
        }
    }

    private void wallCollision(Ball b) {
        double width = 800;
        double height = 600;

        if(b.x - b.radius < 0) { //left/right
            b.x = b.radius;
            b.vx = b.vx * -1;
        }
        if(b.y - b.radius < 0) { //up/down
            b.y = b.radius;
            b.vy = b.vy * -1;
        }
        else if(b.y + b.radius > height) {
            b.y = height - b.radius;
            b.vy = b.vy * -0.8;
        }
    }
}