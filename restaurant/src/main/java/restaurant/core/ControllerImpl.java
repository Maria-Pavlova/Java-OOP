package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double closedBillsSum;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        Food food = null;
        switch (type) {
            case "Salad":
                food = new Salad(name, price);
                break;
            case "VeganBiscuits":
                food = new VeganBiscuits(name, price);
                break;
        }
        HealthyFood healthyFood = healthFoodRepository.foodByName(name);
        if (healthFoodRepository.getAllEntities().contains(healthyFood)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
        }
        healthFoodRepository.add(food);

        return String.format(OutputMessages.FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverages = type.equals("Fresh")
                ? new Fresh(name, counter, brand)
                : new Smoothie(name, counter, brand);

        Beverages beverage = beverageRepository.beverageByName(name, brand);
        if (beverage == null) {
            beverageRepository.add(beverages);
            return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));


    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = type.equals("InGarden")
                ? new InGarden(tableNumber, capacity)
                : new Indoors(tableNumber, capacity);

        Table tableByNumber = tableRepository.byNumber(tableNumber);
        if (tableByNumber == null) {
            tableRepository.add(table);
            return String.format(OutputMessages.TABLE_ADDED, tableNumber);
        }

        throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table table = tableRepository.getAllEntities().stream()
                .filter(t -> !t.isReservedTable() &&
                        t.getSize() >= numberOfPeople)
                .findFirst().orElse(null);

        if (table == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        } else {
            table.reserve(numberOfPeople);
            return String.format(OutputMessages.TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
        }
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {

        Table table = tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood food = healthFoodRepository.foodByName(healthyFoodName);
        if (food == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }

        table.orderHealthy(food);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {

        Table table = tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages drink = beverageRepository.getAllEntities().stream()
                .filter(b -> b.getName().equals(name) && b.getBrand().equals(brand))
                .findFirst().orElse(null);
        if (drink == null) {
            return String.format(OutputMessages.NON_EXISTENT_DRINK, name, brand);
        }

        table.orderBeverages(drink);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {

        Table table = tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        table.clear();
        closedBillsSum += bill;

        return String.format(OutputMessages.BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {

        return String.format(OutputMessages.TOTAL_MONEY, closedBillsSum);
    }
}
