package com.campusdual.racecontrol.model;

import com.campusdual.racecontrol.util.Utils;
import org.campusdual.util.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tournament {
    private String name;
    private List<Race> raceList = new ArrayList<>();
    private List<Garage> garageList = new ArrayList<>();
    private List<Map<Car, Integer>> puntuationList = new ArrayList<>();

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

    //TODO no se está verificando que se añada el mismo garage o la misma carrera
    //TODO habria que añadir excepcion pero me da perecisima
    public void addRace(Race race){
        this.raceList.add(race);
    }

    public void addGarage(Garage garage){
        this.garageList.add(garage);
    }

    public void distributeCars(){
        this.puntuationList.clear();
        if (garageList.isEmpty()){
            System.out.println("El torneo no tiene coches");
        }else if( garageList.size() == 1){
            //TODO distribuir coches cuando solo hay un coche
        }else{
            for (Garage g:
                 garageList) {
                Map<Car, Integer> carMap = new HashMap<>();
                Car c = g.getCarList().get(Utils.random.nextInt(g.getCarList().size()));
                carMap.put(c, 0);
                this.puntuationList.add(carMap);
            }
        }
    }

    public List<Car> getCarList(){
        List<Car> carList = new ArrayList<>();
        for (Map<Car, Integer> carMap : puntuationList) {
            // Agrega el objeto Car a la lista carList
            carList.addAll(carMap.keySet());
        }
        return carList;
    }

    public void simulateTournament(){
        List<Car> carList = getCarList();
        List<Car> simulatedCarList = new ArrayList<>();

        int puntuation = 3;
        for (Race race: raceList) {
            System.out.println("Simulando la carrera " + race.getName() + " . . . ");
            List<Car> winners = race.getWinners(race.simulateRace(carList));
            System.out.println("Los ganadores han sido . . .");
            for (Car winner: winners) {
                System.out.println(winner.getBrand() + " " + winner.getModel() + " " + winner.getDistance() + puntuation);
                for (Map<Car, Integer> carMap : puntuationList) {
                    if (carMap.containsKey(winner)) {
                        int currentScore = carMap.get(winner);
                        carMap.put(winner, currentScore + puntuation); // Suma 1 al valor actual
                    }
                }
                puntuation--;
            }

            System.out.println("------------------------");
            System.out.println("Las puntuaciones hasta ahora son: ");
            for (Map<Car, Integer> carMap : puntuationList) {
                //TODO
                for (Map.Entry<Car, Integer> entry : carMap.entrySet()) {
                    Car car = entry.getKey();
                    int score = entry.getValue();
                    System.out.println(car.getBrand() + " " + car.getModel() + ": " + score);
                }
            }
            System.out.println("-----------------------------");
            Input.string("Enter para continuar . . .");
            puntuation = 3;
        }
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                '}';
    }
}
