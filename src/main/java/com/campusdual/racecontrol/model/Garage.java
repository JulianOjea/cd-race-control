package com.campusdual.racecontrol.model;

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
        car.setGarage(this);
    }
}
