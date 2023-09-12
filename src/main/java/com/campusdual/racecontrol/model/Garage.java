package com.campusdual.racecontrol.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    private String name;
    private List<Car> carList = new ArrayList<>();

    public Garage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void addCar(Car car){
        this.carList.add(car);
        car.setGarageName(this.getName());
    }

    @Override
    public String toString() {
        return "Nombre " + name;
    }

    public JSONObject exportGarage(){
        JSONObject garage = new JSONObject();
        garage.put("garageName", this.name);
        return garage;
    }

    public JSONObject exportGarageWithCars(){
        JSONObject garage = new JSONObject();
        garage.put("garageName", this.name);
        JSONArray array = new JSONArray();
        for (Car car:
             this.carList) {
            //array.add(car.exportScoreCar());
        }
        garage.put("carList", array);
        return garage;
    }
}
