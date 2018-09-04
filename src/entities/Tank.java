package entities;

import java.util.HashMap;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265

/**
 * Created by kacper on 2018-09-04.
 */
public class Tank {
    private int id;
    private HashMap<Integer, Pistol> pistols;
<<<<<<< HEAD
    private double currentFuelVolume; //ilość paliwa z danych TankMeasure
    private double tempCounter; /** licznik pomocniczy, który przechowuje wartość tzw. przebiegu?? zbiornika, suma ilości litrów paliwa
                                która się w nim znajdowała*/
    private double volumeAfterRefuel; //ilość paliwa, która powinna znajdować się w zbiorniku po jego napełnieniu
    private double pumpedOutVolume; //ilość paliwa wypompowana przez wszystkie pistolety z danego zbiornika
    private double balance; /**bilans pomiędzy ilością paliwa, która jest w zbiorniku,
                            a ilością która powinna się w nim znajdować */
=======
    private double currentFuelVolume;
    private double pumpedOutVolume; //ilość paliwa wypompowana przez wszystkie pistolety z danego zbiornika
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265
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
<<<<<<< HEAD

    public double getTempCounter() {
        return tempCounter;
    }

    public void setTempCounter(double tempCounter) {
        this.tempCounter = tempCounter;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getVolumeAfterRefuel() {
        return volumeAfterRefuel;
    }

    public void setVolumeAfterRefuel(double volumeAfterRefuel) {
        this.volumeAfterRefuel = volumeAfterRefuel;
    }
=======
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265
}
