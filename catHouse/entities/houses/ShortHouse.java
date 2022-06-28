package catHouse.entities.houses;

public class ShortHouse extends BaseHouse implements House{
    private static final int capacity = 15;

    public ShortHouse(String name) {
        super(name, capacity);
    }
}
