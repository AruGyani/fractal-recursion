import java.awt.Color;
import java.awt.Graphics2D;

public class Circle {
    private int x, y;
    private float radius;
    
    private Color color;
    private Main m;

    public Circle(int x, int y, float radius, Color color, Main m) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.m = m;
    }

    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) (x - radius + m.getOffX()), (int) (y - radius + m.getOffY()), (int) (2 * radius), (int) (2 * radius));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
