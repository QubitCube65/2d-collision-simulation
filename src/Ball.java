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

        vx = vx + ax * dt;
        vy = vy + ay * dt;

        x = x + vx * dt;
        y = y + vy * dt;


    }
}