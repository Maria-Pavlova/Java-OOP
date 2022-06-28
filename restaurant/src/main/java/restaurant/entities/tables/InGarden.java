package restaurant.entities.tables;

import restaurant.entities.tables.interfaces.Table;

public class InGarden extends BaseTable implements Table {
    private static final double pricePerPerson = 4.50;

    public InGarden(int number, int size) {
        super(number, size, pricePerPerson);
    }
}
