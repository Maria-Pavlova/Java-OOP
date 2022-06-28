package zoo.entities.animals;

public abstract class BaseAnimal implements Animal{
    private String name;
    private String kind;
    private double kg;
    private double price;

    public BaseAnimal(String name, String kind, double kg, double price) {
        this.name = name;
        this.kind = kind;
        this.kg = kg;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setKg(double kg) {
        this.kg = kg;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getKg() {
        return 0;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public void eat() {

    }
}
