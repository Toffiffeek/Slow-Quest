import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class GameMap {
    private final int tileSize;

    private final TileType[][] tiles;

    public GameMap(int tileSize, String mapPath) throws IOException {
        this.tileSize = tileSize;
        MapRepository repository = new MapRepository();
        this.tiles = repository.load(mapPath);
    }

    public int getRows() {
        return tiles.length;
    }

    public int getColumns(){
        return tiles[0].length;
    }

    public boolean isWalkable(int col, int row) {
        if(col < 0 || col >= getColumns() || row < 0 || row >= getRows()){
            return false;
        }
        return tiles[row][col].isWalkable();
    }

    public void draw(Graphics g){
        for (int row = 0; row < getRows(); row++) {
            for(int col = 0; col < getColumns(); col++){
                TileType tile = tiles[row][col];

                g.setColor(tile.getColor());
                g.fillRect(
                        col * tileSize,
                        row * tileSize,
                        tileSize,
                        tileSize
                );
            }
        }
    }
}
