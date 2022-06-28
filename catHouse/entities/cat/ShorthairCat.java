package catHouse.entities.cat;

public class ShorthairCat extends BaseCat implements Cat {
    private static int initialKilograms = 7;


    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, initialKilograms, price);

    }

    @Override
    public void eating() {
       this.setKilograms(this.getKilograms() + 1);
    }
}
