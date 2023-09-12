package com.campusdual.racecontrol;

import com.campusdual.racecontrol.conversors.JsonCarConversor;
import com.campusdual.racecontrol.conversors.JsonGarageConversor;
import com.campusdual.racecontrol.conversors.JsonRaceConversor;
import com.campusdual.racecontrol.model.*;
import com.campusdual.racecontrol.util.Input;
import com.campusdual.racecontrol.util.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RaceControl {

    List<Tournament> tournamentList = new ArrayList<>();
    List<Garage> garageList = new ArrayList<>();
    List<Car> carList = new ArrayList<>();
    List<Race> raceList = new ArrayList<>();

    public RaceControl() {
    }

    public void introductionPrompt(String keyword){
        String str = "";
        switch (keyword) {
            case "garaje":
                str += "3.Mostrar los coches de un garaje\n";
                break;
            case "coche":
                str += "3.Añadir coche a un garaje\n";
                break;
            case "carrera":
                str += "3.Añadir garaje a una carrera\n";
                str += "4.Mostrar garajes de una carrera\n";
                break;
        }

        System.out.println("--------------------------------");
        System.out.println("Este es el menú de gestión de " + keyword);
        System.out.println("Seleccione a donde quiere acceder:");
        System.out.println("1.Crear " + keyword + "\n" +
                "2.Mostrar "+ keyword + "\n" +
                str +
                "0.Salir");
        System.out.println("--------------------------------");
    }
    public void showTournaments(){
        System.out.println("De qué torneo quiere mostrar las carreras?: ");
        for (int i = 0; i < tournamentList.size(); i++) {
            System.out.println(i + ". " + tournamentList.get(i).getName());
        }
    }

    public void showRaces(Tournament t){
        System.out.println("Carreras del torneo: " + t.getName());
        List<Race> raceList = t.getRaceList();
        for (int i = 0; i < raceList.size(); i++) {
            System.out.println(i + ". " + t.getRaceList().get(i).getName());
        }
    }

    public Garage garageSelector(){
        System.out.println("Selecciona un garage de la lista:");
        for (int i = 0; i < garageList.size(); i++) {
            System.out.println(i + ". " + garageList.get(i).getName());
        }
        int input = Input.integer();
        return garageList.get(input);
    }

    public Car carSelector(){
        System.out.println("Selecciona un coche de la lista:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(i + ". " + carList.get(i).getBrand() + " " +
                    carList.get(i).getModel() + " " +
                    carList.get(i).getGarageName());
        }
        int input = Input.integer();
        return carList.get(input);
    }

    public Race createRace(){
        System.out.println("Qué tipo de carrera desea crear?: ");
        System.out.println("1. Estándar");
        System.out.println("2. Eliminación");
        int selector = Input.integer();
        String name = Input.string("Qué nombre desea ponerle?:");

        Race race;

        if(selector == 1){
            race = new StandardRace(name);
        }else{
            race = new EliminationRace(name);
        }
        return race;
    }

    public Race raceSelector(Tournament t){
        System.out.println("Selecciona una carrera de la lista:");
        List<Race> raceList = t.getRaceList();
        for (int i = 0; i < raceList.size(); i++) {
            System.out.println(i + ". " + raceList.get(i).getName());
        }
        int input = Input.integer();
        return raceList.get(input);
    }

    public Tournament tournamentSelector(){
        System.out.println("Selecciona un torneo de la lista:");
        for (int i = 0; i < tournamentList.size(); i++) {
            System.out.println(i + ". " + tournamentList.get(i).getName());
        }
        int input = Input.integer();
        return tournamentList.get(input);
    }

    public void showGarage(Race race){
        for (Garage g:
             race.getGarageList()) {
            System.out.println(g.getName());
        }
    }

    public void racePrompt(){
        boolean flag = true;
        while (flag){
            introductionPrompt("carrera");

            int selector;
            Tournament t;
            Race r;
            switch (Input.integer()) {
                case 1:
                    System.out.println("Seleccione el torneo al que quiere añadir la carrera:");
                    showTournaments();
                    selector = Input.integer();
                    this.tournamentList.get(selector).addRace(createRace());
                    break;
                case 2:
                    System.out.println("Seleccione el torneo del que quiere mostrar las carreras:");
                    showTournaments();
                    selector = Input.integer();
                    showRaces(this.tournamentList.get(selector));
                    break;
                case 3:
                    t = tournamentSelector();
                    r = raceSelector(t);
                    System.out.println("Que garaje desea añadir?");
                    r.addGarage(garageSelector());
                    break;
                case 4:
                    t = tournamentSelector();
                    r = raceSelector(t);
                    showGarage(r);
                    break;
                case 0:
                    flag = false;
                    break;
            }
            Input.string("Presione enter para continuar . . .");
        }
    }

    public void tournamentPrompt(){
        boolean flag = true;
        while (flag){
            introductionPrompt("torneo");
            switch (Input.integer()) {
                case 1:
                    this.tournamentList.add(new Tournament(Input.string("Nombre del torneo: ")));
                    break;
                case 2:
                    System.out.println("Torneos existentes:");
                    for (Tournament t:
                         tournamentList) {
                        System.out.println(t.getName());
                    }
                    System.out.println("\n");
                    break;
                case 0:
                    flag = false;
                    break;
            }
            Input.string("Presione enter para continuar . . .");
        }
    }

    public void garagePrompt(){
        boolean flag = true;
        while (flag){
            introductionPrompt("garaje");
            switch (Input.integer()) {
                case 1:
                    this.garageList.add(new Garage(Input.string("Nombre del garaje: ")));
                    break;
                case 2:
                    System.out.println("Garajes existentes:");
                    for (Garage g:
                            this.garageList) {
                        System.out.println(g.getName());
                    }
                    System.out.println("\n");
                    break;
                case 3:
                    Garage garage = garageSelector();
                    for (Car c:
                         garage.getCarList()) {
                        System.out.println(c.getBrand() + " " + c.getModel());
                    }
                    break;
                case 0:
                    flag = false;
                    break;
            }
            Input.string("Presione enter para continuar . . .");
        }
    }

    public void carPrompt(){
        boolean flag = true;
        while (flag){
            introductionPrompt("coche");
            switch (Input.integer()) {
                case 1:
                    String brand = Input.string("Marca del coche: ");
                    String model = Input.string("Modelo del coche: ");
                    this.carList.add(new Car(brand, model));
                    break;
                case 2:
                    System.out.println("Coches existentes:");
                    for (Car c:
                            this.carList) {
                        System.out.println(c.getBrand() + " " + c.getModel() + " " + c.getGarageName());
                    }
                    System.out.println("\n");
                    break;
                case 3:
                    Car car = carSelector();
                    if (car.getGarageName().isEmpty()){
                        Garage garage = garageSelector();
                        garage.addCar(car);
                    }
                    else{
                        System.out.println("Este coche ya tiene asignado un garaje.");
                    }
                    break;
                case 0:
                    flag = false;
                    break;
            }
            Input.string("Presione enter para continuar . . .");
        }
    }

    public int simulatorRaceSelector(Tournament t){
        System.out.println("Seleccion la carrear a simular:");
        List<Race> raceList = t.getRaceList();
        Race race;
        System.out.println("0. Salir del simulador");
        for (int i = 0; i < raceList.size(); i++) {
            race = raceList.get(i);
            System.out.println((i+1)+". " + race.getName());

            if(!race.getGarageList().isEmpty()){
                System.out.println("\tLista de garajes");
                for (Garage g: race.getGarageList()) {
                    System.out.println("\t\tNombre: " + g.getName());
                }
            }else{
                System.out.println("\t\tEsta carrera no tiene garajes asignados");
            }
            System.out.println("\t - - - - -");
            /*if(!race.getCarList().isEmpty()){
                System.out.println("\tLista de coches");
                for (Car c: race.getCarList()) {
                    System.out.println("\t\tMarca: " + c.getBrand() + " Modelo: " + c.getModel());
                }
            }else{
                System.out.println("\t\tEsta carrera no tiene coches asignados");
            }*/
            System.out.println("\t - - - - -");
            if(!race.getPodium().isEmpty()){
                System.out.println("\t\tPodium:");
                int j = 1;
                for (Car c: race.getPodium()) {

                    System.out.println(j + ". " + "\tMarca: " + c.getBrand() + " Modelo: " + c.getModel());
                }
            }else{
                System.out.println("\t\tEsta carrera aún no ha sido simulada.");
            }
        }
        return Input.integer();
    }

    public void raceSimulatorPrompt(){
        System.out.println("--------------------------------");
        System.out.println("--Desde aquí puede simular una--");
        System.out.println("----------carrera.--------------");
        System.out.println("--------------------------------");
        Tournament t = tournamentSelector();
        //TODO ESTO SE VE HORRIBLE XD
        int exit;
        boolean flag = true;
        while (flag){
            exit = simulatorRaceSelector(t);
            if (exit!=0){
                Race r = t.getRaceList().get(exit-1);

                /*if (r.getCarList().isEmpty()){
                    System.out.println("Ha seleccionado la carrera " + r.getName());
                    System.out.println("Como aún no tiene coches asignados se procederá a distribuirlos");
                    System.out.println("Pulse \"enter\" para continuar");
                    Input.string();
                    //r.distributeRaceCars();
                }else{
                    if (r.getPodium().isEmpty()){
                        System.out.println("--------------------------------\n\n");
                        System.out.println("Ha seleccionado la carrera " + r.getName());
                        System.out.println("Es una carrera de tipo: " + r.getType());
                        System.out.println("Ready...");
                        System.out.println("Set...");
                        Input.string("(Presiona enter)");
                        System.out.println("GO!...");

                        System.out.println("-----------------------");
                        System.out.println("\n\n Resultados. . .");
                        //List<Car> actualRace = r.simulateRace(r.getCarList());
                        //for (Car c:
                          //   actualRace) {
                            //System.out.println("El coche " + c.getBrand() + " " + c.getModel() + " ha recorrido "
                              //      + c.getDistance() + " kilómetros.");
                        //}
                        System.out.println("-----------------------");
                        Input.string("(Presiona enter para ver ganadores . . .)");
                        System.out.println("ey");
                    } else {
                        System.out.println("Esta carrera ya ha sido simulada");
                    }
                }*/

            }else{
                flag=false;
            }
        }

    }

    public void mainPrompt(){
        boolean flag = true;
        while (flag){
            System.out.println("--------------------------------");
            System.out.println("Bienvenido al menú principal de la gestión de torneos y carreras!");
            System.out.println("Seleccione a donde quiere acceder:");
            System.out.println("1.Torneo\n" +
                    "2.Carrera\n" +
                    "3.Garage\n" +
                    "4.Coche\n" +
                    "5.Simular carreras\n" +
                    "0.Salir");
            System.out.println("--------------------------------");
            switch (Input.integer()) {
                case 1:
                    tournamentPrompt();
                    break;
                case 2:
                    racePrompt();
                    break;
                case 3:
                    garagePrompt();
                    break;
                case 4:
                    carPrompt();
                    break;
                case 5:
                    raceSimulatorPrompt();
                    break;
                case 0:
                    carPrompt();
                    flag = false;
                    break;
            }
        }
    }

    public void raceSimulation(){
        System.out.println("Seleccione la carrera que quiere simular: ");
        Race r = Utils.showAndSelectFromList(this.raceList, false).get(0);
        System.out.println("Esta carrera tiene los siguientes garajes asignados:");
        List<Garage> g = r.getGarageList();
        Utils.showFromList(g, true);
        r.distributeAndSimulate(g);
    }

    public void tournamentSimulation(){
        System.out.println("Seleccione el torneo que quiere simular: ");
        Tournament t = Utils.showAndSelectFromList(this.tournamentList, false).get(0);
        System.out.println("Esta torneo tiene las siguientes carreras:");
        Utils.showFromList(t.getRaceList(), true);
        System.out.println("Esta torneo tiene los siguientes garajes:");
        Utils.showFromList(t.getGarageList(), true);
        System.out.println("Distribuyendo coches . . .");
        t.distributeCars();
        System.out.println("Se han distribuido los siguientes coches: ");
        Utils.showFromList(t.getCarList(), true);
        t.simulateTournament();
    }

    public void trueMainprompt(){
        System.out.println("--------------------------------");
        System.out.println("Bienvenido al menú principal de la gestión de torneos y carreras!");
        System.out.println("Seleccione a donde quiere acceder:");
        System.out.println("1.Simular Carrera\n" +
                "2.Simular torneo\n" +
                "0.Salir");
        System.out.println("--------------------------------");
        boolean flag = true;
        while (flag) {
            switch (Input.integer()) {
                case 1:
                    raceSimulation();
                    break;
                case 2:
                    tournamentSimulation();
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }
    }

    public void exporter() throws IOException, ParseException {
        /*JsonCarConversor jsoncar = new JsonCarConversor();

        Car c0 = new Car("Seat", "Ibiza", "Pinguinos azules");
        Car c1 = new Car("Opel", "Meriva", "Pinguinos azules");
        Car c2 = new Car("Renault", "306", "Caballos violetas");
        Car c3 = new Car("Honda", "Civic", "Caballos violetas");
        Car c4 = new Car("Rojo", "coche", "Leones verdes");
        Car c5 = new Car("Amarillo", "coche", "Leones verdes");
        Car c6 = new Car("Azul", "coche", "Capibaras grises");
        Car c7 = new Car("Verde", "coche", "Capibaras grises");

        List<Car> c = new ArrayList<>(Arrays.asList(c0, c1, c2, c3, c4, c5, c6, c7));
        jsoncar.exportCars(c);*/

        /*
        Garage g0 = new Garage("Pinguinos azules");
        Garage g1 = new Garage("Caballos violetas");
        Garage g2 = new Garage("Leones verdes");
        Garage g3 = new Garage("Capibaras grises");
        List<Garage> c = new ArrayList<>(Arrays.asList(g0, g1, g2, g3));

        JsonGarageConversor.exportGarages(c);

         */

        Race r0 = new StandardRace("Mortal Race");
        Race r1 = new EliminationRace("Demonic Race");
        Race r2 = new StandardRace("Eternal Race");
        List<Race> r = new ArrayList<>(Arrays.asList(r0, r1, r2));
        r0.addGarage(this.garageList.get(0));
        r0.addGarage(this.garageList.get(1));
        r0.addGarage(this.garageList.get(2));
        r0.addGarage(this.garageList.get(3));
        r1.addGarage(this.garageList.get(2));
        r1.addGarage(this.garageList.get(3));
        r1.addGarage(this.garageList.get(0));
        r1.addGarage(this.garageList.get(1));
        r2.addGarage(this.garageList.get(0));
        //JsonRaceConversor.exportRaces(r);

        System.out.println("Exportado");
    }

    public void importer() throws IOException, ParseException {
        JsonCarConversor jsoncar = new JsonCarConversor();
        List<Car> cars = jsoncar.importCar();
        this.carList.addAll(cars);

        List<Garage> garages = JsonGarageConversor.importGarages();
        this.garageList.addAll(garages);

        for (Car c: this.carList) {
            for (Garage g:this.garageList) {
                if (c.getGarageName().equals(g.getName())){
                    g.addCar(c);
                }
            }
        }

        List<Race> races = JsonRaceConversor.importRaces(this.garageList);
        this.raceList.addAll(races);

        //Tournament t0 = new Tournament("Retired Normies");
        //Tournament t1 = new Tournament("Caribean Cocktails");
        Tournament t2 = new Tournament("Infernal Monsters");
        //tournamentList.add(t0);
        //tournamentList.add(t1);
        tournamentList.add(t2);

        t2.addRace(this.raceList.get(0));
        //t2.addRace(this.raceList.get(1));
        t2.addRace(this.raceList.get(2));

        t2.addGarage(this.garageList.get(0));
        t2.addGarage(this.garageList.get(1));
        t2.addGarage(this.garageList.get(2));
    }

    public static void main(String[] args) throws IOException, ParseException {
        RaceControl rc = new RaceControl();
        rc.importer();
        //rc.exporter();
        rc.trueMainprompt();
        //rc.mainPrompt();
    }
}
