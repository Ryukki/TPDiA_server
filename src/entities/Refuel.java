package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ryukki on 03.07.2018.
 */
public class Refuel extends BaseClass implements Serializable{
    private Date measureDate;

    public Date getMeasureDate() {
        return measureDate;
    }

    public Double getTankingSpeed() {
        return tankingSpeed;
    }

    private Double tankingSpeed;

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    public void setTankingSpeed(Double tankingSpeed) {
        this.tankingSpeed = tankingSpeed;
    }
}
