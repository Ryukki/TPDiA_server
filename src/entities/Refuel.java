package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ryukki on 03.07.2018.
 */
public class Refuel implements Serializable{
    private Date measureDate;
    private Integer tankId;
    private Double fuelVolume;

    public Date getMeasureDate() {
        return measureDate;
    }

    public Integer getTankId() {
        return tankId;
    }

    public Double getFuelVolume() {
        return fuelVolume;
    }

    public Double getTankingSpeed() {
        return tankingSpeed;
    }

    private Double tankingSpeed;

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public void setFuelVolume(Double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public void setTankingSpeed(Double tankingSpeed) {
        this.tankingSpeed = tankingSpeed;
    }
}
