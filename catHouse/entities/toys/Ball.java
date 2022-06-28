package catHouse.entities.toys;

public class Ball extends BaseToy implements Toy{
    private static final int softness = 1;
    private static final double price = 10;

    public Ball() {
        super(softness, price);
    }
}
