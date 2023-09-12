package com.campusdual.racecontrol.model;

import java.util.List;
import java.util.Random;

public class StandardRace extends Race{

    public static final String STANDARD = "Standard";

    public StandardRace(String name) {
        super(name);
        this.setType(STANDARD);
    }

    public StandardRace(String name, List<Garage> garageList) {
        super(name, garageList);
        this.setType(STANDARD);
    }

    @Override
    public List<Car> simulateRace(List<Car> carList) {
        int time = 0;

        while (time < Race.DURATION){
            time+=1;
            for (Car c: carList) {
                c.calculateVelocity();
            }
        }
        return carList;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.getName() + " - Tipo:" + this.getType();
    }
}
