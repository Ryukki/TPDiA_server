package entities;

/**
 * Created by kacper on 2018-09-04.
 */
public class Divergence {
    private double fuelInTankVolume;
    private double soldFuelVolume;

    public double getFuelInTankVolume() {
        return fuelInTankVolume;
    }

    public void setFuelInTankVolume(double fuelInTankVolume) {
        this.fuelInTankVolume = fuelInTankVolume;
    }

    public double getSoldFuelVolume() {
        return soldFuelVolume;
    }

    public void setSoldFuelVolume(double soldFuelVolume) {
        this.soldFuelVolume = soldFuelVolume;
    }
}
