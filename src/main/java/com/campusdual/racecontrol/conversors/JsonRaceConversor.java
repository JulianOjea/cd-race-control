package com.campusdual.racecontrol.conversors;

import com.campusdual.racecontrol.model.EliminationRace;
import com.campusdual.racecontrol.model.Garage;
import com.campusdual.racecontrol.model.Race;
import com.campusdual.racecontrol.model.StandardRace;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonRaceConversor {

    public static final String RACE_FILE_NAME = "race.json";

    public static void exportRaces(List<Race> raceList){
        JSONArray jsonArray = new JSONArray();
        for (Race r : raceList){
            jsonArray.add(raceToJson(r));
        }

        JsonUtils.exportJsonArray(jsonArray, JsonRaceConversor.RACE_FILE_NAME);
    }
    public static JSONObject raceToJson(Race race){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", race.getName());
        jsonObject.put("type", race.getType());

        JSONArray garageArray = new JSONArray();

        for (Garage garage : race.getGarageList()) {
            JSONObject garageObject = new JSONObject();
            garageObject.put("garageName", garage.getName());
            garageArray.add(garageObject);
        }

        jsonObject.put("garageList", garageArray);
        return jsonObject;
    }

    public static List<Race> importRaces(List<Garage> garages) throws IOException, ParseException {

        List<Race> raceList = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(JsonRaceConversor.RACE_FILE_NAME)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String name = jsonObj.get("name").toString();
                String type = jsonObj.get("type").toString();

                JSONArray garageArray = (JSONArray) jsonObj.get("garageList");

                List<Garage> garageList = new ArrayList<>();

                for (Object garageObj : garageArray) {
                    JSONObject garageJson = (JSONObject) garageObj;
                    String garageName = garageJson.get("garageName").toString();
                    // Agregar otros campos de Garage si es necesario

                    for (Garage g:garages) {
                        if (garageName.equals(g.getName())){
                            garageList.add(g);
                        }
                    }
                }
                if (type.equals(EliminationRace.ELIMINATION)){
                    raceList.add(new EliminationRace(name, garageList));
                }else{
                    raceList.add(new StandardRace(name, garageList));
                }
            }
        }

        return raceList;
    }
}
