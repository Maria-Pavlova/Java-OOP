package panzer.models;

import panzer.contracts.Part;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public abstract class Parts implements Part {
    private String model;
    private double weight;
    private DecimalFormat price;

    public Parts(String model, double weight, DecimalFormat price) {
        this.model = model;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String getModel() {
        return null;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }
}
