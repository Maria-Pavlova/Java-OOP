package zoo.repositories;

import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;

public class FoodRepositoryImpl implements FoodRepository{
    private Collection<Food> foods;

    public FoodRepositoryImpl() {
        this.foods = new ArrayList<>();
    }

    @Override
    public void add(Food food) {

    }

    @Override
    public boolean remove(Food food) {
        return false;
    }

    @Override
    public Food findByType(String type) {
        return null;
    }
}
