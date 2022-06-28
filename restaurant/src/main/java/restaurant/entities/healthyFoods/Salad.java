package restaurant.entities.healthyFoods;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

public class Salad extends Food implements HealthyFood {
    private static final double InitialSaladPortion = 150;

    public Salad(String name,  double price) {
        super(name, InitialSaladPortion, price);
    }
}
