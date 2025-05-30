import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //edge smoothening

        int groundHeight = 20;
        g2d.setColor(new Color(200,200,200));
        // g2d.fillRect(0, getHeight() - groundHeight, getWidth(), groundHeight);

        /*
        GradientPaint bg = new GradientPaint( //uncomment for greyish background gradient
                0, 0, new Color(230, 230, 230),
                0, getHeight(), new Color(120, 120, 120)
        );
        g2d.setPaint(bg);
        g2d.fillRect(0, 0, getWidth(), getHeight()); */

        for (Ball b : balls) {
            float centerX = (float) b.x;
            float centerY = (float) b.y;
            float radius = (float) b.radius;

            Color centerColor = b.color.brighter();
            Color edgeColor = b.color;

            RadialGradientPaint paint = new RadialGradientPaint(
                    new Point2D.Float(centerX, centerY),
                    radius,
                    new float[] {0f, 1f},
                    new Color[] {centerColor, edgeColor}
            );
            g2d.setPaint(paint);
            g2d.fillOval(
                    (int)(b.x - b.radius),
                    (int)(b.y - b.radius),
                    (int)(2 * b.radius),
                    (int)(2 * b.radius)
            );
        }
    }
}

/*
 * @Override
 *     protected void paintComponent(Graphics g) {
 *         super.paintComponent(g);
 *         Graphics2D g2d = (Graphics2D) g;
 *         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //edge straightening
 *
 *         int groundHeight = 20;
 *         g2d.setColor(new Color(200,200,200));
 *         //g2d.fillRect(0, getHeight() - groundHeight, getWidth(), groundHeight);
 *
 *         for(Ball b : balls) {
 *             g2d.setColor(b.color);
 *             g2d.fillOval(
 *                     (int)(b.x - b.radius),
 *                     (int)(b.y - b.radius),
 *                     (int)(2 * b.radius),
 *                     (int)(2 * b.radius)
 *             );
 *         }
 *     }*/