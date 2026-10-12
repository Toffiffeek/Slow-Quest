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

    //Maps
    private final String FOREST_MAP = "/maps/forest.map";
    private final String VILLAGE_MAP = "/maps/village.map";
    private String currentMapPath = VILLAGE_MAP;
    private GameMap gameMap;

    private Player player;
    private long lastMoveTime = 0;
    private final long MOVE_DELAY = 150; //miliseconds


    public GamePanel() {
        try { URL imageUrl = getClass().getResource("/sprites/AddictedBoyFront.png");

            if (imageUrl == null) {
                throw new IOException("Sprite AddictedBoyFront.png not found");
            }
            AddictedBoyFront = ImageIO.read(imageUrl);
        }
        catch (IOException e) { e.printStackTrace();
        }
        try { URL imageUrl = getClass().getResource("/sprites/AddictedBoyBack.png");

            if (imageUrl == null) {
                throw new IOException("Sprite AddictedBoyBack.png not found");
            }
            AddictedBoyBack = ImageIO.read(imageUrl);
        }
        catch (IOException e) { e.printStackTrace();
        }
        loadMap(VILLAGE_MAP);
        player = new Player(
                7 * TILE_SIZE, 9* TILE_SIZE,
                TILE_SIZE,
                AddictedBoyFront, AddictedBoyBack,
                AddictedBoyFront, AddictedBoyBack);
        setPreferredSize(new Dimension(TILE_SIZE * gameMap.getColumns(), TILE_SIZE * gameMap.getRows()));
        setBackground(Color.decode("#c4cfc8")); move("UP", 0, -TILE_SIZE);
        move("DOWN", 0, TILE_SIZE); move("LEFT", -TILE_SIZE, 0);
        move("RIGHT", TILE_SIZE, 0); move("W", 0, -TILE_SIZE);
        move("S", 0, TILE_SIZE); move("A", -TILE_SIZE, 0);
        move("D", TILE_SIZE, 0);
    }

    private void loadMap(String mapPath){
        try{
            gameMap = new GameMap(TILE_SIZE, mapPath);
            currentMapPath = mapPath;
        } catch(IOException e){
            throw new RuntimeException("Could not load map: " + mapPath, e);
        }
    }

    private void changeMap(String targetMap, int targetCol, int targetRow){
        loadMap(targetMap);

        player.setPosition(targetCol * TILE_SIZE, targetRow * TILE_SIZE);
        revalidate();
        repaint();
    }

    private void checkMapTransition(){
        int col = player.getX() / TILE_SIZE;
        int row = player.getY() / TILE_SIZE;

        System.out.println("Map: " + currentMapPath + "col: " + col + "row: " + row);
        //village -> forest transition
        if (currentMapPath.equals(VILLAGE_MAP) && col == 7 && row == 0){
            changeMap(FOREST_MAP, 7, 9);
        }
        else if (currentMapPath.equals(VILLAGE_MAP) && col == 8 && row == 0){
            changeMap(FOREST_MAP, 8, 9);
        }
        //forest -> village transition
        else if (currentMapPath.equals(FOREST_MAP) && col == 7 && row == 10){
            changeMap(VILLAGE_MAP, 7, 1);
        }
        else if (currentMapPath.equals(FOREST_MAP) && col == 8 && row == 10){
            changeMap(VILLAGE_MAP, 8, 1);
        }
    }

    private void move(String pressedKey, int dx, int dy){
        getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(pressedKey), pressedKey);

        getActionMap().put(pressedKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();

                if(currentTime - lastMoveTime < MOVE_DELAY){
                    return;
                }
                lastMoveTime = currentTime;

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
                    checkMapTransition();
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