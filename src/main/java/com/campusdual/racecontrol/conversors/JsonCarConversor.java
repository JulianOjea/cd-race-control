package com.campusdual.racecontrol.conversors;

import com.campusdual.racecontrol.model.Car;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TODO small refactor of this class :/
public class JsonCarConversor {

    public static final String CAR_FILE_NAME = "cars.json";
    //public FileWriter fw = new FileWriter(CAR_FILE_NAME);
    //public Reader reader = new FileReader(CAR_FILE_NAME);
    JSONParser parser = new JSONParser();

    public JsonCarConversor() throws IOException, ParseException {
    }

    public void exportCar(Car car){
        JSONObject jo = carToJson(car);
        try (FileWriter fw = new FileWriter(JsonCarConversor.CAR_FILE_NAME)){
            fw.write(jo.toJSONString());
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportCars(List<Car> carList){
        JSONArray jsonArray = new JSONArray();

        for (Car car : carList) {
            jsonArray.add(carToJson(car));
        }

        try (FileWriter fw = new FileWriter(JsonCarConversor.CAR_FILE_NAME)){
            fw.write(jsonArray.toJSONString());
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject carToJson(Car car){
        JSONObject jsonCar = new JSONObject();
        jsonCar.put("brand", car.getBrand());
        jsonCar.put("model", car.getModel());
        jsonCar.put("garageName", car.getGarageName());

        return jsonCar;
    }

    public List<Car> importCar() throws IOException, ParseException {

        List<Car> carList = new ArrayList<>();

        try (FileReader reader = new FileReader(JsonCarConversor.CAR_FILE_NAME)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String brand = jsonObj.get("brand").toString();
                String model = jsonObj.get("model").toString();
                String garageName = jsonObj.get("garageName").toString();

                carList.add(new Car(brand, model, garageName));
            }
        }

        return carList;
    }
}
