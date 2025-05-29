import javax.swing.*;
import java.util.*;

public class Main {

    public static void main(String args[]) {
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(100, 100, 50, 30, 20, 1));
        balls.add(new Ball(300, 200, -40, -20, 30, 2));
        balls.add(new Ball(500, 150, -30, -40, 50, 3));

        PhysicsEngine engine = new PhysicsEngine(balls);
        SimulationPanel panel = new SimulationPanel(balls);

        JFrame frame = new JFrame("2d collision simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        final long[] lastTime = {System.nanoTime()}; //stores timestamp of previous frame

        javax.swing.Timer timer = new javax.swing.Timer(16, e -> {
            long now = System.nanoTime();
            double dt = (now - lastTime[0]) / 1_000_000_000.0; //real elapsed time in seconds
            lastTime[0] = now;

            dt = Math.min(dt, 0.05); //optional clamp to avoid large jumps

            engine.simulate(dt); //optional good fixed value: 0.016 (60fps)
            panel.repaint();
        });
        timer.start();
    }
}