import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

    private int x;
    private int y;

    private final int tileSize;
    private BufferedImage sprite;

    public Player(int startX, int startY, int tileSize, BufferedImage sprite) {
        this.x = startX;
        this.y = startY;
        this.tileSize = tileSize;
        this.sprite = sprite;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }

    public void draw(Graphics g, java.awt.Component observer) {
        if(sprite != null){
            g.drawImage(
                    sprite,
                    x,
                    y,
                    tileSize,
                    tileSize,
                    observer
            );
        }
    }
}
