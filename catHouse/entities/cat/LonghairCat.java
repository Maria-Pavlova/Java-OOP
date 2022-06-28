package catHouse.entities.cat;

public class LonghairCat extends BaseCat implements Cat {
    private static int initialKilograms = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, initialKilograms, price);
    }


    @Override
    public void eating() {
        this.setKilograms( this.getKilograms() + 3);
    }
}