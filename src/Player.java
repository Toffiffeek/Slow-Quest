import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Player {

    private int x;
    private int y;

    private final int tileSize;
    private final BufferedImage frontSprite;
    private final BufferedImage backSprite;
    private final BufferedImage leftSprite;
    private final BufferedImage rightSprite;
    private BufferedImage sprite;

    public Player(
            int startX,
            int startY,
            int tileSize,
            BufferedImage frontSprite,
            BufferedImage backSprite,
            BufferedImage leftSprite,
            BufferedImage rightSprite
    ) {
        this.x = startX;
        this.y = startY;
        this.tileSize = tileSize;
        this.frontSprite = frontSprite;
        this.backSprite = backSprite;
        this.leftSprite = leftSprite;
        this.rightSprite = rightSprite;
        this.sprite = frontSprite;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy){
        x += dx;
        y += dy;

        if(dy < 0){
            sprite = backSprite;
        }
        else if(dy > 0){
            sprite = frontSprite;
        }
        else if(dx < 0){
            sprite = leftSprite;
        }
        else if(dx > 0){
            sprite = rightSprite;
        }
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
