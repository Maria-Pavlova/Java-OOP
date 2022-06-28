package catHouse.entities.toys;

public class Mouse extends BaseToy implements Toy {
    private static final int softness = 5;
    private static final double price = 15;

    public Mouse() {
        super(softness, price);
    }
}
