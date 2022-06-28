package panzer.models;

import panzer.contracts.Part;
import panzer.contracts.Vehicle;

import java.math.BigDecimal;

public abstract class Vehicles implements Vehicle {
    private String model;
    private  double weight;
    private double price;
    private int attack;
    private int defense;
    private int hitPoints;

    public Vehicles(String model, double weight, double price, int attack, int defense, int hitPoints) {
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getTotalWeight() {
        return weight;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public long getTotalAttack() {
        return 0;
    }

    @Override
    public long getTotalDefense() {
        return 0;
    }

    @Override
    public long getTotalHitPoints() {
        return 0;
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {

    }

    @Override
    public void addShellPart(Part shellPart) {

    }

    @Override
    public void addEndurancePart(Part endurancePart) {

    }

    @Override
    public Iterable<Part> getParts() {
        return null;
    }
}
