import java.awt.Color;

public enum TileType {
    GRASS("#779e5c",true),
    GRASS_HIGH("#548046",true),
    WALL("#695a5b",false),
    TREE("#385237",false),
    BUILDING("#8f704a",false),
    DOORS("#ffffff", true);

    private final Color color;
    private final boolean walkable;

    TileType(String hexColor, boolean walkable){
        this.color = Color.decode(hexColor);
        this.walkable = walkable;
    }

    public Color getColor(){
        return color;
    }

    public boolean isWalkable(){
        return walkable;
    }
}
