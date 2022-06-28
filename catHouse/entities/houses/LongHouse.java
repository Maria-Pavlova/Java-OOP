package catHouse.entities.houses;

public class LongHouse extends BaseHouse implements House{
    private static final int capacity = 30;

    public LongHouse(String name) {
        super(name, capacity);
    }
}
