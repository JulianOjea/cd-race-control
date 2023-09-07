package com.campusdual.racecontrol.model;

import java.util.List;

public class EliminationRace extends Race{
    public EliminationRace(String name, List<Garage> garageList, List<Car> carList) {
        super(name, garageList, carList);
    }

    public EliminationRace(String name) {
        super(name);
    }
}
