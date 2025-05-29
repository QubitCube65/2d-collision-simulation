import javax.swing.*;
import java.util.*;

public class Main {

    public static void main(String args[]) {
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(100, 100, 50, 30, 20, 1));
        balls.add(new Ball(300, 200, -40, -20, 30, 2));

        PhysicsEngine engine = new PhysicsEngine(balls);
        SimulationPanel panel = new SimulationPanel(balls);

        JFrame frame = new JFrame("2d collision simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        javax.swing.Timer timer = new javax.swing.Timer(16, e -> {
            engine.simulate(0.016);
            panel.repaint();
        });
        timer.start();
    }
}