package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.*;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Map<Integer, Computer> computers;
    private Map<Integer, Component> components;
    private Map<Integer, Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new LinkedHashMap<>();
        this.components = new LinkedHashMap<>();
        this.peripherals = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer;
        switch (computerType) {
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        if (computers.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }else {
            computers.put(id, computer);
            return String.format(ADDED_COMPUTER, id);
        }
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        checkIsComputerIdNotExist(computerId);
     Peripheral peripheral;
        switch (peripheralType){
            case "Headset":
                peripheral = new Headset(id,manufacturer,model,price,overallPerformance,connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id,manufacturer,model,price,overallPerformance,connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id,manufacturer,model,price,overallPerformance,connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id,manufacturer,model,price,overallPerformance,connectionType);
                break;
            default:throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }
        if (peripherals.containsKey(id)){
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }
            peripherals.put(id,peripheral);
        computers.get(computerId).addPeripheral(peripheral);
            return String.format(ADDED_PERIPHERAL,peripheralType,id,computerId);

    }


    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        checkIsComputerIdNotExist(computerId);
        Computer computer = computers.get(computerId);
        Peripheral peripheral = computer.removePeripheral(peripheralType);
        peripherals.remove(peripheral.getId());
        return String.format(REMOVED_PERIPHERAL,peripheralType,peripheral.getId());

    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        checkIsComputerIdNotExist(computerId);
        Component component;
        switch (componentType){
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "Motherboard":
                component = new Motherboard(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id,manufacturer,model,price,overallPerformance,generation);
                break;
            case "VideoCard":
                component = new VideoCard(id,manufacturer,model,price,overallPerformance,generation);
                break;
            default:throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }
        if (components.containsKey(id)){
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }
            components.put(id,component);
            computers.get(computerId).addComponent(component);

            return String.format(ADDED_COMPONENT,componentType,id,computerId);

    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        checkIsComputerIdNotExist(computerId);
        Computer computer = computers.get(computerId);
        Component component = computer.removeComponent(componentType);
        components.remove(component.getId());
        return String.format(REMOVED_COMPONENT,componentType,component.getId());
    }

    @Override
    public String buyComputer(int id) {
        checkIsComputerIdNotExist(id);
        Computer computer = computers.remove(id);
        return computer.toString();


    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> computerList = this.computers.values().stream()
                .filter(c -> c.getPrice() <= budget).collect(Collectors.toList());
        if (computerList.isEmpty()){
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER,budget));
        }
        List<Computer> list = computerList.stream().sorted(Comparator.comparingDouble(Computer::getOverallPerformance).reversed())
                .limit(1).collect(Collectors.toList());
        Computer computer = list.get(0);
        computers.remove(computer.getId());

        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        checkIsComputerIdNotExist(id);
        Computer computer = computers.get(id);
        return computer.toString();
    }
    private void checkIsComputerIdNotExist(int computerId) {
        if (!computers.containsKey(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
    }
}
