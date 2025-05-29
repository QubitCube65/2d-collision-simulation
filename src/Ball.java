import java.awt.Color;
import java.util.Random;

public class Ball {

    public double x;
    public double y;
    public double vx;
    public double vy;
    public double ax;
    public double ay;
    public double radius;
    public double mass;
    public Color color;
    private static final double ROLLING_FRICTION_COEFFICIENT = 0.01;

    private static final double GRAVITY = 980.0;

    public Ball(double x, double y, double vx, double vy, double radius, double mass) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = 0;
        this.ay = GRAVITY;
        this.radius = radius;
        this.mass = mass;

        Random rand = new Random();
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public void update(double dt) {

        double dragFactor = 0.00002; //not realistic!
        double v = Math.sqrt(vx * vx + vy * vy);
        if(v != 0) {
            double fx = -dragFactor * v * vx;
            double fy = -dragFactor * v * vy;
            ax = fx / mass;
            ay = GRAVITY + fy / mass;
        }
        else {
            ax = 0;
            ay = GRAVITY;
        }

        vx = vx + ax * dt;
        vy = vy + ay * dt;

        x = x + vx * dt;
        y = y + vy * dt;

        double groundLevel = 600.0;
        if(y + radius >= groundLevel - 0.5 && Math.abs(vy) < 10) {
            double normalForce = mass * GRAVITY;
            double friction = ROLLING_FRICTION_COEFFICIENT * normalForce;
            double sign = Math.signum(vx);
            double frictionAcceleration = friction / mass;
            if(Math.abs(vx) < frictionAcceleration * dt) {
                vx = 0;
            }
            else {
                vx = vx - sign * frictionAcceleration * dt;
            }
        }
    }
}