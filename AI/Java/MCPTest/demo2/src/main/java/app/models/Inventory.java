package app.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Inventory {
    
    private Map<String, ItemInfo> store;

    public Inventory() {
        try(var reader = new BufferedReader(new FileReader("medstore.csv"))){
            store = reader.lines()
                .skip(1)
                .filter(line -> line.length() > 0)
                .map(line -> line.split(","))
                .collect(Collectors.toMap(segs -> segs[0].toLowerCase(), segs -> new ItemInfo(
                    Double.parseDouble(segs[1]), 
                    Integer.parseInt(segs[2])
                )));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public Map<String, ItemInfo> store() {
        return store;
    }
}
