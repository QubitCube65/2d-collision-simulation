import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationPanel extends JPanel{
    private List<Ball> balls;

    public SimulationPanel(List<Ball> balls) {
        this.balls = balls;
        setPreferredSize(new Dimension(800,600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Ball b : balls) {
            g.setColor(Color.BLUE);
            g.fillOval(
                    (int)(b.x - b.radius),
                    (int)(b.y - b.radius),
                    (int)(2 * b.radius),
                    (int)(2 * b.radius)
            );
        }
    }
}