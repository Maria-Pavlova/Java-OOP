package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer implements Explorer{

    private static final double initialEnergy = 40;


    public GlacierExplorer(String name) {
        super(name, initialEnergy);
    }
}
