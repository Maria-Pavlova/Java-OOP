package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;


import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public void addComponent(Component component) {

            Component componentToAdd = components.stream()
                    .filter(c -> c.getClass().getSimpleName().equals(component.getClass().getSimpleName()))
                    .findFirst().orElse(null);
            if (componentToAdd != null) {
    throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                    component.getClass().getSimpleName() ,this.getClass().getSimpleName(),this.getId()));
            }
        components.add(component);
    }


    @Override
    public Component removeComponent(String componentType) {
        Component component = components.stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);
        if (component == null){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType, this.getClass().getSimpleName(),this.getId()));
// TODO check
        }

         components.remove(component);
        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.stream().anyMatch(p-> p.getClass() == peripheral.getClass())){
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                    peripheral.getClass().getSimpleName() ,this.getClass().getSimpleName(),this.getId()));
        }
        peripherals.add(peripheral);

    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = peripherals.stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheralType))
                .findFirst().orElse(null);
        if (peripheral == null){
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    peripheralType,this.getClass().getSimpleName(), getId()));
        }
        peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public double getOverallPerformance() {

        double average = components.stream().mapToDouble(Component::getOverallPerformance).average().orElse(0);
        return super.getOverallPerformance() + average;
    }

    @Override
    public double getPrice() {
        double sumComp = components.stream().mapToDouble(Component::getPrice).sum();
        double sumPer = peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
        return super.getPrice() + sumComp + sumPer;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(PRODUCT_TO_STRING,this.getOverallPerformance(),getPrice(),getClass().getSimpleName(),
                        getManufacturer(),getModel(),getId())).append(System.lineSeparator())
                .append(String.format(" Components (%d):", components.size()))
                .append(System.lineSeparator());
        for (Component component : components) {
            builder.append("  " + component.toString()).append(System.lineSeparator());
        }

        double average = peripherals.stream().mapToDouble(Product::getOverallPerformance).average().orElse(0);

        builder.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f):",
                peripherals.size(), average)).append(System.lineSeparator());
        for (Peripheral peripheral : peripherals) {
            builder.append("  " + peripheral.toString()).append(System.lineSeparator());

        }


        return builder.toString().trim();
    }
}
