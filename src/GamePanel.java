import java.net.URL;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    private BufferedImage AddictedBoyFront;
    private BufferedImage AddictedBoyBack;
    //size config
    private final int TILE_SIZE = 64;
    private final int COLUMNS = 16;
    private final int ROWS = 11;

    //map
    private final int[][] MAP_ONE = {
            {1,1,1,1,1,1,1,3,0,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,2,2,0,0,2,2,0,0,0,0,1},
            {1,0,0,0,0,2,2,0,0,2,2,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,2,2,0,0,2,2,0,0,0,0,1},
            {1,0,0,0,0,2,2,0,0,2,2,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    //coordinates
    private int x = TILE_SIZE;
    private int y = TILE_SIZE;


    public GamePanel() {
        try { URL imageUrl = getClass().getResource("/AddictedBoyFront.png");

            if (imageUrl == null) {
                throw new IOException("Sprite AddictedBoyFront.png not found");
            }
            AddictedBoyFront = ImageIO.read(imageUrl);
        }
        catch (IOException e) { e.printStackTrace();
        }
        try { URL imageUrl = getClass().getResource("/AddictedBoyBack.png");

            if (imageUrl == null) {
                throw new IOException("Sprite AddictedBoyBack.png not found");
            }
            AddictedBoyBack = ImageIO.read(imageUrl);
        }
        catch (IOException e) { e.printStackTrace();
        }
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        setBackground(Color.decode("#c4cfc8")); move("UP", 0, -TILE_SIZE);
        move("DOWN", 0, TILE_SIZE); move("LEFT", -TILE_SIZE, 0);
        move("RIGHT", TILE_SIZE, 0); move("W", 0, -TILE_SIZE);
        move("S", 0, TILE_SIZE); move("A", -TILE_SIZE, 0);
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

    private void speak(String pressedKey, int x, int y, BufferedImage oldImage, BufferedImage newImage){
        if(!pressedKey.equals("SPACE")){
            return;
        }
        if(isNearInteracitve(32, 32)){
        }
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {

                int tileX = col * TILE_SIZE;
                int tileY = row * TILE_SIZE;

                if (MAP_ONE[row][col] == 1) {
                    g.setColor(Color.decode("#b1d080"));
                    g.fillRect(
                            tileX, tileY,
                            TILE_SIZE, TILE_SIZE
                    );

                }
                else if(MAP_ONE[row][col] == 2) {
                    g.setColor(Color.decode("#9c3d40"));
                    g.fillRect(
                            tileX, tileY,
                            TILE_SIZE, TILE_SIZE
                    );
                }
                else if(MAP_ONE[row][col] == 3) {
                    g.setColor(Color.decode("#69405f"));
                    g.fillRect(
                            tileX, tileY,
                            TILE_SIZE, TILE_SIZE
                    );
                }
                else {
                    g.setColor(Color.decode("#323635"));
                    g.fillRect(
                            tileX, tileY,
                            TILE_SIZE, TILE_SIZE
                    );
                }
            }
        }

        if(AddictedBoyFront != null){
            g.drawImage(
                    AddictedBoyFront,
                    x,
                    y,
                    TILE_SIZE,
                    TILE_SIZE,
                    this
            );
        }
    }

    private boolean isNearInteracitve(int interactiveX, int interactiveY){
        int distanceX = Math.abs(x - interactiveX);
        int distanceY = Math.abs(y - interactiveY);

        return distanceX + distanceY == TILE_SIZE;
    }
}
