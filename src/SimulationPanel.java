import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationPanel extends JPanel{
    private List<Ball> balls;

    public SimulationPanel(List<Ball> balls) {
        this.balls = balls;
        setPreferredSize(new Dimension(800,600));
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //edge straightening

        int groundHeight = 20;
        g2d.setColor(new Color(200,200,200));
        //g2d.fillRect(0, getHeight() - groundHeight, getWidth(), groundHeight);

        for(Ball b : balls) {
            g2d.setColor(b.color);
            g2d.fillOval(
                    (int)(b.x - b.radius),
                    (int)(b.y - b.radius),
                    (int)(2 * b.radius),
                    (int)(2 * b.radius)
            );
        }
    }
}