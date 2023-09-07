package com.campusdual.racecontrol.model;

import java.util.List;

public class StandardRace extends Race{
    public StandardRace(String name, List<Garage> garageList, List<Car> carList) {
        super(name, garageList, carList);
    }

    public StandardRace(String name) {
        super(name);
    }
}
