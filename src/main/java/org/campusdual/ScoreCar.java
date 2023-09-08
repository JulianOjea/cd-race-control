package org.campusdual;

import com.campusdual.racecontrol.model.Car;
import org.campusdual.util.Input;
import org.campusdual.util.Utils;

import java.util.Random;

public class ScoreCar implements Comparable<ScoreCar>{

    private String brand;
    private String model;
    private String garageName = "";
    public static final int MAX_VELOCITY = 200;
    private int speedometer = 0;
    private double distance = 0.0;

    public ScoreCar() {
        this.brand = Input.string("Marca del coche: ");
        this.model = Input.string("Modelo del coche: ");
    }

    public ScoreCar(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "ScoreCar{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", garageName='" + garageName + '\'' +
                '}';
    }

    public void speedUp(){
        if(this.speedometer<ScoreCar.MAX_VELOCITY){
            speedometer+=10;
        }
    }

    public void slowDown(){
        if(this.speedometer>0){
            speedometer-=10;
        }
    }

    public void calculateVelocity(){
        int isAccelerating = Utils.getRandomNumberInRange(1, 3);

        if(isAccelerating != 2){
            speedUp();
        }else {
            slowDown();
        }
        updateDistance();
    }

    public void updateDistance(){
        distance += speedometer * 16.667;
    }

    public int getSpeedometer() {
        return speedometer;
    }

    public void setSpeedometer(int speedometer) {
        this.speedometer = speedometer;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    /*
    public static void main(String[] args) {
        //ScoreCar c = new ScoreCar();
        //System.out.println(c);

        ScoreCar c1 = new ScoreCar("Seat", "Ibiza");
        System.out.println(c1);

        ScoreCar c2 = new ScoreCar("Peugeot", "200");
        System.out.println(c2);

        for (int i = 0; i < 120; i++) {
            c1.calculateVelocity();
            c2.calculateVelocity();
        }

        System.out.println("Velocidad final tras 12 min: " + c1.getSpeedometer() + " distancia " + c1.getDistance());
        System.out.println("Velocidad final tras 12 min: " + c2.getSpeedometer() + " distancia " + c2.getDistance());
        System.out.println(c1.compareTo(c2));

    }
*/
    @Override
    public int compareTo(ScoreCar o) {
        if (this.getDistance() > o.getDistance()){
            return 1;
        }else if (this.getDistance() < o.getDistance()){
            return -1;
        }else {
            return 0;
        }
    }
}
