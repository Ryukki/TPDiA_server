package entities;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kacper on 2018-09-04.
 */
public class Tank {
    private int id;
    private HashMap<Integer, Pistol> pistols;
    private double currentFuelVolume;
    private double pumpedOutVolume; //ilość paliwa wypompowana przez wszystkie pistolety z danego zbiornika
    private int fuelTemperature;

    public Tank() {
        pistols = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, Pistol> getPistols() {
        return pistols;
    }

    public void setPistols(HashMap<Integer, Pistol> pistols) {
        this.pistols = pistols;
    }

    public double getCurrentFuelVolume() {
        return currentFuelVolume;
    }

    public void setCurrentFuelVolume(double currentFuelVolume) {
        this.currentFuelVolume = currentFuelVolume;
    }

    public int getFuelTemperature() {
        return fuelTemperature;
    }

    public void setFuelTemperature(int fuelTemperature) {
        this.fuelTemperature = fuelTemperature;
    }

    public double getPumpedOutVolume() {
        return pumpedOutVolume;
    }

    public void setPumpedOutVolume(double pumpedOutVolume) {
        this.pumpedOutVolume = pumpedOutVolume;
    }
}
