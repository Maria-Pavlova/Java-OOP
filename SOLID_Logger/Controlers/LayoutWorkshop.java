package SOLID_Logger.Controlers;

import SOLID_Logger.Interfaces.Layout;
import SOLID_Logger.Interfaces.LayoutFactory;

public class LayoutWorkshop implements LayoutFactory {
    @Override
    public Layout produce(String type) {
        return switch (type){
            case "SimpleLayout" -> new SimpleLayout();
            case "XmlLayout" -> new XmlLayout();
            default ->  throw new IllegalStateException(
                    "Invalid type of layout");
        };
    }
}
