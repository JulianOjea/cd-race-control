package com.campusdual.racecontrol.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Race {

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

    public void distributeRaceCars(){
        if(garageList.size()==1){
            carList.addAll(garageList.get(0).getCarList());
        } else if (garageList.isEmpty()) {
            System.out.println("No se pueden distribuir los coches debido a que esta carrera no tiene ningún garaje");
        } else {
            //TODO estoy aqui, distribuir de manera aleatoria todo
        }
    }
}
