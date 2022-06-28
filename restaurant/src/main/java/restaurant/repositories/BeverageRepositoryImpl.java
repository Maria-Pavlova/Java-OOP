package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.*;
import java.util.stream.Collectors;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    Map<String,Beverages> beveragesMap;

    public BeverageRepositoryImpl() {
        this.beveragesMap = new LinkedHashMap<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return beveragesMap.get(drinkName);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(beveragesMap.values());
    }

    @Override
    public void add(Beverages beverages) {
    beveragesMap.put(beverages.getName(), beverages);
    }
}
