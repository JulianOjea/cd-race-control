package com.campusdual.racecontrol.conversors;

import com.campusdual.racecontrol.model.Garage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonGarageConversor {

    public static final String GARAGE_FILE_NAME = "garage.json";

    public static JSONObject garageToJson(Garage garage){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", garage.getName());

        return jsonObject;
    }

    public static void exportGarages(List<Garage> garageList){
        JSONArray jsonArray = new JSONArray();
        for (Garage g: garageList) {
            jsonArray.add(garageToJson(g));
        }

        JsonUtils.exportJsonArray(jsonArray, JsonGarageConversor.GARAGE_FILE_NAME);
    }

    public static List<Garage> importGarages() throws IOException, ParseException {

        List<Garage> garageList = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(JsonGarageConversor.GARAGE_FILE_NAME)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String name = jsonObj.get("name").toString();

                garageList.add(new Garage(name));
            }
        }

        return garageList;
    }
}