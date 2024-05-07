package entity;

import core.ComboItem;

public class Model {
    private int id;
    private int brand_id;
    private String name;
    private String year;
    private Type type;
    private Fuel fuel;
    private Gear gear;
    private Brand brand;

    public enum Fuel {
        GASOLINE,
        LPG,
        ELECTRIC,
        DIESEL
    }

    public enum Gear {
        MANUEL,
        AUTO
    }

    public enum Type {
        SEDAN,
        HACBACK
    }

    public Model(int id, int brand_id, String name, String year, Type type, Fuel fuel, Gear gear, Brand brand) {
        this.id = id;
        this.brand_id = brand_id;
        this.name = name;
        this.year = year;
        this.type = type;
        this.fuel = fuel;
        this.gear = gear;
        this.brand = brand;
    }

    public Model(){

    }

    public int getId() {
        return id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public Type getType() {
        return type;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public Gear getGear() {
        return gear;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getId(),this.getBrand().getName() + " - " + this.getName() + " - " + this.getYear() + " - " + this.getGear());
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", brand_id=" + brand_id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", type=" + type +
                ", fuel=" + fuel +
                ", gear=" + gear +
                ", brand=" + brand +
                '}';
    }
}
