package com.campusdual.racecontrol.model;

public class Car {
    private String brand;
    private String model;
    private Garage garage;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.garage = new Garage("NoGarage");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }


}
