import java.awt.Graphics2D;
import java.awt.Color;

public class Line {
    private float x, y, length;
    private Main m;

    public Line(float x, float y, float length, Main m) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.m = m;
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) x + m.getOffX(), (int) y + m.getOffY(), (int) (x + length) + m.getOffX(), (int) y + m.getOffY());
    }
}
