package com.campusdual.racecontrol.model;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private String name;
    private List<Race> raceList = new ArrayList<>();
    private List<Garage> garageList = new ArrayList<>();

    public Tournament(String name, List<Race> raceList, List<Garage> garageList) {
        this.name = name;
        this.raceList = raceList;
        this.garageList = garageList;
    }

    public Tournament(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Race> getRaceList() {
        return raceList;
    }

    public List<Garage> getGarageList() {
        return garageList;
    }

    public void addRace(Race race){
        this.raceList.add(race);
    }

    public void addgarageList(Garage garage){
        this.garageList.add(garage);
    }
}
