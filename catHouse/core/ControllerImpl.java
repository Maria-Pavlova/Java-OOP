package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;


import static catHouse.common.ConstantMessages.UNSUITABLE_HOUSE;
import static catHouse.common.ExceptionMessages.*;


public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
       switch (type){
           case "ShortHouse":
               houses.add(new ShortHouse(name));
               return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
           case "LongHouse":
               houses.add(new LongHouse(name));
               return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
           default:throw new NullPointerException(INVALID_HOUSE_TYPE);
       }

    }

    @Override
    public String buyToy(String type) {
        switch (type){
            case "Ball":
                toys.buyToy(new Ball());
                return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE,type);
            case "Mouse":
                toys.buyToy(new Mouse());
                return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE,type);
            default:throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        if (toy == null){
           throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND,toyType));
       }
        for (House house : houses) {
            if (house.getName().equals(houseName)){
                house.buyToy(toy);
            }
        }
        toys.removeToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        if (!isValidCat(catType)){
            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
        Cat cat = null;
        if ("ShorthairCat".equals(catType)) {
          cat = new ShorthairCat(catName, catBreed, price);
        } else if ("LonghairCat".equals(catType)) {
            cat = new LonghairCat(catName, catBreed, price);
        }
        House house = getHouseByName(houseName);

        String houseType = house.getClass().getSimpleName();

        boolean isValidHouse = houseType.equals("LongHouse") && catType.equals("LonghairCat")
                || houseType.equals("ShortHouse") && catType.equals("ShorthairCat");
        if (isValidHouse){
            house.addCat(cat);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }else {
            return UNSUITABLE_HOUSE;
        }
    }

    @Override
    public String feedingCat(String houseName) {
        House house = getHouseByName(houseName);

        Collection<Cat> cats = house.getCats();
        house.feeding();
        return String.format(ConstantMessages.FEEDING_CAT,cats.size());
    }



    @Override
    public String sumOfAll(String houseName) {
        House house = getHouseByName(houseName);
        Collection<Toy> toys = house.getToys();
        Collection<Cat> cats = house.getCats();

        double toysPrice = toys.stream().mapToDouble(Toy::getPrice).sum();
        double catsPrice = cats.stream().mapToDouble(Cat::getPrice).sum();
        return String.format(ConstantMessages.VALUE_HOUSE,houseName,toysPrice+catsPrice);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        for (House house : houses) {
          builder.append(house.getStatistics()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
    private boolean isValidCat(String type){
        return type.equals("ShorthairCat")|| type.equals("LonghairCat");
    }
    private House getHouseByName(String houseName) {
        House house = houses.stream().filter(h -> h.getName().equals(houseName))
                .findFirst()
                .orElse(null);
        return house;
    }
}
