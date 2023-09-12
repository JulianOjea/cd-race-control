package com.campusdual.racecontrol.model;

import com.campusdual.racecontrol.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EliminationRace extends Race {
    public static final int WARM_UP = 20;
    public static final String ELIMINATION = "Elimination";

    public EliminationRace(String name) {
        super(name);
        this.setType(ELIMINATION);
    }

    public EliminationRace(String name, List<Garage> garageList) {
        super(name, garageList);
        this.setType(ELIMINATION);
    }

    @Override
    public List<Car> simulateRace(List<Car> cars)  {
        int time = 0;
        List<Car> winners = new ArrayList<Car>();

        while (time < EliminationRace.WARM_UP) {
            time += 1;
            for (Car c :
                    cars) {
                c.calculateVelocity();
            }
        }
        System.out.println("Coches despues del warm up");
        Utils.showFromList(cars, true);

        while (!cars.isEmpty()) {
            time++;
            for (Car c :
                    cars) {
                c.calculateVelocity();
            }

            Collections.sort(cars);
            Collections.reverse(cars);
            System.out.println("Coches tras el minuto " + time);
            Utils.showFromList(cars, true);

            if (cars.size() <= 3) {
                System.out.println("a ganadores se añadio: " + cars.get(cars.size()-1).getBrand());
                winners.add(cars.get(cars.size()-1));
            }
            System.out.println("Se va a eliminar el coche " + cars.get(cars.size() - 1).getBrand());
            cars.remove(cars.size() - 1);
        }
        Collections.reverse(winners);
        return winners;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.getName() + " - Tipo:" + this.getType();
    }
}
