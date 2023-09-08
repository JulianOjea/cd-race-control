package com.campusdual.racecontrol.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Race {


    public static final int DURATION = 180;

    private String type;
    private String name;
    private List<Garage> garageList = new ArrayList<>();
    private List<Car> carList = new ArrayList<>();
    private List<Car> podium = new ArrayList<>();

    public Race(String name, List<Garage> garageList, List<Car> carList) {
        this.name = name;
        this.garageList = garageList;
        this.carList = carList;
    }
    public Race(String name) {
        this.name = name;
    }

    public void addCar(Car car){
        this.carList.add(car);
    }

    public void addGarage(Garage garage){
        this.garageList.add(garage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Garage> getGarageList() {
        return garageList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public List<Car> getPodium() {
        return podium;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void distributeRaceCars(){
        Random rand = new Random();
        if(garageList.size()==1){
            carList.addAll(garageList.get(0).getCarList());
        } else if (garageList.isEmpty()) {
            System.out.println("No se pueden distribuir los coches debido a que esta carrera no tiene ningún garaje");
        } else {
            for (Garage g:
                 garageList) {
                List<Car> garageCarList = g.getCarList();
                if (!g.getCarList().isEmpty()){
                    carList.add(garageCarList.get(rand.nextInt(garageCarList.size())));
                }
            }
        }
    }

    public abstract List<Car> simulateRace(List<Car> car) ;
}
