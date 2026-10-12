public class MapTransition {
    private final int col;
    private final int row;
    private final String targetMap;
    private final int targetCol;
    private final int targetRow;

    public MapTransition(
            int col,
            int row,
            String targetMap,
            int targetCol,
            int targetRow){
        this.col = col;
        this.row = row;
        this.targetMap = targetMap;
        this.targetCol = targetCol;
        this.targetRow = targetRow;
    }

    public boolean matches(int playerCol, int playerRow){
        return col == playerCol && row == playerRow;
    }

    public String getTargetMap(){
        return targetMap;
    }

    public int getTargetCol(){
        return targetCol;
    }

    public int getTargetRow(){
        return targetRow;
    }
}
