import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    //size config
    private final int TILE_SIZE = 32;
    private final int COLUMNS = 32;
    private final int ROWS = 20;

    //map
    private final int[][] MAP_ONE = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,1},
            {1,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,1},
            {1,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,1},
            {1,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,1},
            {1,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,1},
            {1,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,1},
            {1,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    //coordinates
    private int x = TILE_SIZE;
    private int y = TILE_SIZE;


    public GamePanel() {
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        setBackground(Color.decode("#c4cfc8"));
        move("UP", 0, -TILE_SIZE);
        move("DOWN", 0, TILE_SIZE);
        move("LEFT", -TILE_SIZE, 0);
        move("RIGHT", TILE_SIZE, 0);
        move("W", 0, -TILE_SIZE);
        move("S", 0, TILE_SIZE);
        move("A", -TILE_SIZE, 0);
        move("D", TILE_SIZE, 0);
    }

    private void move(String pressedKey, int dx, int dy){
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(pressedKey), pressedKey);

        getActionMap().put(pressedKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nextX = x + dx;
                int nextY = y + dy;

                int col = nextX / TILE_SIZE;
                int row = nextY / TILE_SIZE;

                if (MAP_ONE[row][col] == 0) {
                    x = nextX;
                    y = nextY;
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {

                int tileX = col * TILE_SIZE;
                int tileY = row * TILE_SIZE;

                if (MAP_ONE[row][col] == 1) {
                    g.setColor(Color.decode("#b1d080"));

                }
                else if(MAP_ONE[row][col] == 2) {
                    g.setColor(Color.decode("#9c3d40"));
                }
                else if(MAP_ONE[row][col] == 3) {
                    g.setColor(Color.decode("#cca047"));
                }
                else {
                    g.setColor(Color.decode("#323635"));
                }

                g.fillRect(
                        tileX, tileY,
                        TILE_SIZE, TILE_SIZE
                );
            }
        }

        g.setColor(Color.decode("#a07fba"));
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
    }
}