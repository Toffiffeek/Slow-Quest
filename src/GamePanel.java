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
    private GameMap gameMap;

    private Player player;


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
        try{
            gameMap = new GameMap(TILE_SIZE, "/maps/village.map");
        } catch (IOException e) {
            throw new RuntimeException("Could not load game map", e);
        }
        player = new Player(TILE_SIZE, TILE_SIZE,TILE_SIZE, AddictedBoyFront);
        setPreferredSize(new Dimension(TILE_SIZE * gameMap.getColumns(), TILE_SIZE * gameMap.getRows()));
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
                int nextX = player.getX() + dx;
                int nextY = player.getY() + dy;

                int col = nextX / TILE_SIZE;
                int row = nextY / TILE_SIZE;

                //checking for the end of the map
                if (col < 0 || col >= gameMap.getColumns() ||
                        row < 0 || row >= gameMap.getRows()) {
                    repaint();
                    return;
                }

                //checking if the given coordinates are walkable
                if (gameMap.isWalkable(col, row)) {
                    player.move(dx, dy);
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //painting the map
        gameMap.draw(g);

        //painting the player
        player.draw(g, this);
    }

}