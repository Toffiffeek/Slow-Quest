import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    private int x = 32;
    private int y = 32;
    private final int speed = 32;

    public GamePanel() {
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.BLACK);
        move("UP", 0, -speed);
        move("DOWN", 0, speed);
        move("LEFT", -speed, 0);
        move("RIGHT", speed, 0);
        move("W", 0, -speed);
        move("S", 0, speed);
        move("A", -speed, 0);
        move("D", speed, 0);
    }

    private void move(String pressedKey, int dx, int dy){
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(pressedKey), pressedKey);

        getActionMap().put(pressedKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x += dx;
                y += dy;

                x = Math.clamp(x, 0, getWidth() - 32);
                y = Math.clamp(y, 0, getHeight() - 32);

                repaint();
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(x, y, 32, 32);
    }
}