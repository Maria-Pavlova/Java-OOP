package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer implements Explorer{
    private static final double INITIAL_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
    super.setEnergy(Math.max(0, this.getEnergy() - 7));
    }
}
