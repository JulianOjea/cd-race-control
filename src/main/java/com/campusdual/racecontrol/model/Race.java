package com.campusdual.racecontrol.model;

import com.campusdual.racecontrol.util.Utils;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Race {
    public static final int DURATION = 180;

    private String type;
    private String name;
    private List<Garage> garageList = new ArrayList<>();
    private List<Car> podium = new ArrayList<>();


    public Race(String name, List<Garage> garageList) {
        this.name = name;
        this.garageList = garageList;
    }

    public Race(String name) {
        this.name = name;
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

    public List<Car> getPodium() {
        return podium;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void distributeAndSimulate(List<Garage> garageList){
        //Primero se vacian todos los coches
        podium.clear();

        List<Car> carList = distributeRaceCars(garageList);
        System.out.println("Coches que se han distribuido: ");
        Utils.showFromList(carList, true);
        System.out.println("Resultado de la carrera");
        List<Car> carListSimulated = simulateRace(carList);

        getWinners(carListSimulated);
    }

    //A partir de una lista de coches simulados te devuelve el podio con 3 ganadores ordenados de mayor a menor
    //es decir el index = 0, es el gandor ..

    public List<Car> getWinners(List<Car> carListSimulated){
        Collections.sort(carListSimulated);
        Collections.reverse(carListSimulated);
        //Utils.showFromList(carListSimulated, true);
        System.out.println("----Se ha terminado la carrera-----");
        System.out.println("Ganadores");
        int i = 0;
        for (Car c:carListSimulated) {
            podium.add(i, carListSimulated.get(i));
            i++;
            if (i==3) break;
        }
        Utils.showFromList(podium, true);
        System.out.println("------------------------------------");

        return podium;
    }

    private List<Car> distributeRaceCars(List<Garage> garageList){
        List<Car> carList = new ArrayList<>();
        if(garageList.size()==1){
            carList.addAll(garageList.get(0).getCarList());
        } else if (garageList.isEmpty()) {
            System.out.println("No se pueden distribuir los coches debido a que esta carrera no tiene ningún garaje");
        } else {
            for (Garage g:
                 garageList) {
                List<Car> garageCarList = g.getCarList();
                if (!g.getCarList().isEmpty()){
                    carList.add(garageCarList.get(Utils.random.nextInt(garageCarList.size())));
                }
            }
        }
        return carList;
    }

    //Le pasas una lista de coches y te simula la carrera devolviendo los coches con la velocidad y distancia final
    //esto no devuelve los coches ordenados por ganador
    abstract List<Car> simulateRace(List<Car> carList) ;
}
