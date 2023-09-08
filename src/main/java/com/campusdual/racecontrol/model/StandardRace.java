package com.campusdual.racecontrol.model;

import java.util.List;
import java.util.Random;

public class StandardRace extends Race{
    public StandardRace(String name, List<Garage> garageList, List<Car> carList) {
        super(name, garageList, carList);
        this.setType("Standard");
    }

    public StandardRace(String name) {
        super(name);
    }


    @Override
    public List<Car> simulateRace(List<Car> cars) {
        int time = 0;
        cars = this.getCarList();

        double velKmMin = 0;
        while (time < Race.DURATION){
            time+=1;
            for (Car c: this.getCarList()) {
                c.calculateVelocity();
            }
        }
        return cars;
    }
}
