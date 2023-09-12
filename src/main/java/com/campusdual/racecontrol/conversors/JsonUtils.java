package com.campusdual.racecontrol.conversors;

import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

public class JsonUtils {

    public static void exportJsonArray(JSONArray jsonArray, String filename){
        try (FileWriter fw = new FileWriter(filename)){
            fw.write(jsonArray.toJSONString());
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
