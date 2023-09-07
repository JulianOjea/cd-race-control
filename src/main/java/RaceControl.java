import com.campusdual.racecontrol.model.*;
import util.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RaceControl {

    List<Tournament> tournamentList = new ArrayList<>();
    List<Garage> garageList = new ArrayList<>();
    List<Car> carList = new ArrayList<>();

    public RaceControl() {
        Tournament t0 = new Tournament("Retired Normies");
        Tournament t1 = new Tournament("Caribean Cocktails");
        Tournament t2 = new Tournament("Infernal Monsters");
        tournamentList.add(t0);
        tournamentList.add(t1);
        tournamentList.add(t2);
        t2.addRace(new EliminationRace("Mortal Race"));
        t2.addRace(new EliminationRace("Demonic Race"));
        t2.addRace(new StandardRace("Eternal Race"));

        Garage g0 = new Garage("Pinguinos azules");
        Garage g1 = new Garage("Caballos violetas");
        Garage g2 = new Garage("Leones verdes");
        Garage g3 = new Garage("Capibaras grises");
        garageList.addAll(Arrays.asList(g0,g1,g2,g3));

        Car c0 = new Car("Seat", "Ibiza");
        Car c1 = new Car("Opel", "Meriva");
        Car c2 = new Car("Renault", "306");
        Car c3 = new Car("Honda", "Civic");
        Car c4 = new Car("Porsche", "Carrera");
        carList.addAll(Arrays.asList(c0,c1,c2,c3,c4));

        g0.addCar(c0);
        g0.addCar(c1);
        g1.addCar(c2);
        g1.addCar(c3);

        t2.getRaceList().get(0).addGarage(g0);
        t2.getRaceList().get(0).addGarage(g1);
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
                    carList.get(i).getGarage().getName());
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
                        System.out.println(c.getBrand() + " " + c.getModel() + " " + c.getGarage().getName());
                    }
                    System.out.println("\n");
                    break;
                case 3:
                    Car car = carSelector();
                    if (car.getGarage().getName().equals("NoGarage")){
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
                case 0:
                    carPrompt();
                    flag = false;
                    break;
            }
        }
    }
    public static void main(String[] args) {
        RaceControl rc = new RaceControl();
        rc.mainPrompt();
    }
}
