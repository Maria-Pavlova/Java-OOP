package restaurant.entities.tables;

import restaurant.entities.tables.interfaces.Table;

public class Indoors extends BaseTable implements Table {
    private static final double pricePerPerson = 3.50;

    public Indoors(int number, int size) {
        super(number, size, pricePerPerson);
    }
}
