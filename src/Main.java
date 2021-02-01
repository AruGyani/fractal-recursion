import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements MouseWheelListener, KeyListener {
    private static final long serialVersionUID = 1;
    private static final int WIDTH = 1280, HEIGHT = 720;
    
    private boolean left = false, right = false, up = false, down = false;
    private int offX = 0, offY = 0;
    private int speed = 5;
    int scale = 1;

    private ArrayList<Circle> circleGeneration = new ArrayList<Circle>();
    private ArrayList<Line> lineGeneration = new ArrayList<Line>();
    private Color[] colors = {new Color(43, 52, 47), new Color(29, 77, 80), new Color(70, 162, 113), new Color(165, 212, 142), new Color(220, 230, 113)};

    public Main() {
        addMouseWheelListener(this);
        addKeyListener(this);

        setFocusable(true);        

        //drawCircle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 100);
        cantor(100, 100, WIDTH - 190);
    }

    public void cantor(float x, float y, float length) {
        if (length > 1) {
            lineGeneration.add(new Line(x, y, length, this));

            y += 40;

            cantor(x, y, length / 3.0f);
            cantor(x + (length * (2.0f / 3.0f)), y, length / 3.0f);
        }
    }

    public void drawCircle(int x, int y, float radius) {
        //g.setColor(new Color((int) (Math.random() * 0x1000000)));
        int randomColor = (int) (Math.random() * colors.length);
        circleGeneration.add(new Circle(x, y, radius, colors[randomColor], this));

        if (radius > 2) {
            // Left and Right
            drawCircle((int) (x + radius + radius/2), y, radius / 2);
            drawCircle((int) (x - radius - (radius / 2)), y, radius / 2);

            // Above and Below
            drawCircle(x, (int) (y + radius + radius/2), radius / 2);
            drawCircle(x, (int) (y - radius - radius/2), radius / 2);
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.translate(WIDTH / 2, HEIGHT / 2);
        g2.scale(scale, scale);
        g2.translate(-WIDTH / 2, -HEIGHT / 2);

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        // for (Circle c : circleGeneration) {
        //     if (c.getX() >= 0 && c.getX() <= WIDTH) {
        //         if (c.getY() >= 0 && c.getY() <= HEIGHT) {
        //             c.render(g2);
        //         }
        //     }
        // }

        for (Line l : lineGeneration) {
            l.render(g2);
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();

        if (notches < 0) {
            scale += 1;
        } else {
            if (scale > 0) {
                scale -= 1;
            }
        }
    }

    public void update() {
        if (left) offX += speed;
        if (right) offX -= speed;
        if (up) offY += speed;
        if (down) offY -= speed;

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Main c = new Main();

        c.setDoubleBuffered(true);
        frame.add(c);

        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while(true) {
            try { Thread.sleep(50); c.update(); }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) up = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) up = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
    }

    public int getOffX() {
        return offX;
    }

    public int getOffY() {
        return offY;
    }
}
