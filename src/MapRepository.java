import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MapRepository {
    public TileType[][] load(String resourcePath) throws IOException{
        InputStream stream = getClass().getResourceAsStream(resourcePath);

        if(stream == null){
            throw new IOException("Map not found: " + resourcePath);
        }

        List<TileType[]> rows = new ArrayList<TileType[]>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))){

            String line;
            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()){
                    continue;
                }

                TileType[] row = new TileType[line.length()];

                for(int col = 0; col < line.length(); col++){
                    char symbol = line.charAt(col);

                    switch(symbol){
                        case '0':
                            row[col] = TileType.GRASS;
                            break;
                        case '1':
                            row[col] = TileType.TREE;
                            break;
                        case '2':
                            row[col] = TileType.BUILDING;
                            break;
                        case '3':
                            row[col] = TileType.WALL;
                            break;
                        case '4':
                            row[col] = TileType.GRASS_HIGH;
                            break;
                        default:
                            throw new IOException("Unknown map symbol" + symbol);
                    }
                }

                rows.add(row);
            }
        }
        if(rows.isEmpty()){
            throw new IOException("Map is empty: " + resourcePath);
        }
        int width = rows.get(0).length;
        for(TileType[] row : rows){
            if(row.length != width){
                throw new IOException("Map rows have different lengths: " + resourcePath);
            }
    }
    return rows.toArray(new TileType[rows.size()][]);
}
}
