package entities;

/**
 * Created by kacper on 2018-09-04.
 */
public class Pistol {
    private double pumpedOutVolume; //ilość paliwa wypompowana przez pojedynczy pistolet

    public double getPumpedOutVolume() {
        return pumpedOutVolume;
    }

    public void setPumpedOutVolume(double pumpedOutVolume) {
        this.pumpedOutVolume = pumpedOutVolume;
    }
}
