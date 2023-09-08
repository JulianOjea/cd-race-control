package com.campusdual.racecontrol.model;

import java.util.List;

public class EliminationRace extends Race{
    public EliminationRace(String name, List<Garage> garageList, List<Car> carList) {
        super(name, garageList, carList);
        this.setType("Elimination");
    }

    public EliminationRace(String name) {
        super(name);
    }

    @Override
    public List<Car> simulateRace(List<Car> car) {
        return null;
    }
}
