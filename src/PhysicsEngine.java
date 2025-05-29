import java.util.List;

public class PhysicsEngine {
    private List<Ball> balls;

    public PhysicsEngine(List<Ball> balls) {
        this.balls = balls;
    }

    /**Executes the simulation steps
     * */
    public void simulate(double dt) {
        for(Ball b : balls) {
            b.update(dt); //update position & velocity with acceleration
            wallCollision(b); //handle collision with screen
        }
        for(int i = 0; i < balls.size(); i++) { //check for collision between all pairs of balls
            for(int j = i+1; j < balls.size(); j++) {
                resolveCollision(balls.get(i), balls.get(j)); //handle ball-ball collision
            }
        }
    }


    private void wallCollision(Ball b) {
        double width = 800;
        double height = 600;

        if(b.x - b.radius < 0) { //left/right
            b.x = b.radius; //snap back inside
            b.vx = b.vx * -1; //reflect horizontal velocity
        }
        if(b.y - b.radius < 0) { //up/down
            b.y = b.radius;
            b.vy = b.vy * -1; //reflect horizontal velocity
        }
        else if(b.y + b.radius > height) {
            b.y = height - b.radius;
            b.vy = b.vy * -0.8; //dampen bounce because energy loss
        }
    }

    private void resolveCollision(Ball a, Ball b) {
        double dx = b.x - a.x; //distance along x
        double dy = b.y - a.y; //distance along y
        double distance = Math.sqrt(dx * dx + dy * dy); //actual distance
        double minDist = a.radius +b.radius; //min distance without overlap

        if(distance < minDist) { //for overlapping balls
            double nx = dx / distance; //normalize collision vector
            double ny = dy / distance;
            double dvx = b.vx - a.vx; //relative velocity between two balls
            double dvy = b.vy - a.vy;
            double dot = dvx * nx + dvy * ny;

            if(dot < 0) { //if balls are moving toward each other
                double totalMass = a.mass + b.mass;
                double impulse = (2 * dot) / totalMass; //compute impulse scalar (elastic collision formula)
                a.vx = a.vx + impulse * b.mass * nx; //apply impulse to each ball
                a.vy = a.vy + impulse * b.mass * ny;
                b.vx = b.vx - impulse * a.mass * nx;
                b.vy = b.vy - impulse * a.mass * ny;

                double overlap = 0.5 * (minDist - distance); //push balls apart so they dont overlap
                a.x = a.x - overlap * nx;
                a.y = a.y - overlap * ny;
                b.x = b.x + overlap * nx;
                b.y = b.y + overlap * ny;
            }
        }
    }
}