import java.net.URL;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    //sprites
    private BufferedImage AddictedBoyFront;
    private BufferedImage AddictedBoyBack;

    //Window size config size config
    private final int TILE_SIZE = 64;
    private final int COLUMNS = 16;
    private final int ROWS = 11;

    //map
    private final TileType[][] MAP_ONE = {
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.BUILDING, TileType.BUILDING, TileType.GRASS, TileType.GRASS, TileType.BUILDING, TileType.BUILDING, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.BUILDING, TileType.BUILDING, TileType.GRASS, TileType.GRASS, TileType.BUILDING, TileType.BUILDING, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.BUILDING, TileType.BUILDING, TileType.GRASS, TileType.GRASS, TileType.BUILDING, TileType.BUILDING, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.BUILDING, TileType.BUILDING, TileType.GRASS, TileType.GRASS, TileType.BUILDING, TileType.BUILDING, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.WALL},
            {TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL, TileType.WALL}
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

                //checking for the end of the map
                if (col < 0 || col >= COLUMNS ||
                        row < 0 || row >= ROWS) {
                    repaint();
                    return;
                }

                //checking if the given coordinates are walkable
                if (MAP_ONE[row][col].isWalkable()) {
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

        //painting the map
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {

                int tileX = col * TILE_SIZE;
                int tileY = row * TILE_SIZE;

                g.setColor(MAP_ONE[row][col].getColor());
                g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
            }

            //painting the player
            if (AddictedBoyFront != null) {
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

    }}