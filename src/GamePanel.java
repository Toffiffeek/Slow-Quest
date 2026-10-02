import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    public GamePanel() {
        setBackground(Color.BLACK);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(300, 240, 32, 32);
    }
}