package logics;

import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ryukki on 17.08.2018.
 */
public class DataGrouper {

    public Map<Integer, AllMeasures> groupMeasuresByTankId(AllMeasures allMeasures){
        Map<Integer, AllMeasures> returnMap = new HashMap();
        Integer tankId;
        for (NozzleMeasure nozzleMeasure: allMeasures.getNozzleMeasures()){
            tankId = nozzleMeasure.getTankId();
            if(!returnMap.containsKey(tankId)){
                returnMap.put(tankId, new AllMeasures());
            }
            returnMap.get(tankId).getNozzleMeasures().add(nozzleMeasure);
        }
        for (TankMeasure tankMeasure: allMeasures.getTankMeasures()){
            tankId = tankMeasure.getTankId();
            returnMap.get(tankId).getTankMeasures().add(tankMeasure);
        }
        for (Refuel refuel: allMeasures.getRefuels()){
            tankId = refuel.getTankId();
            returnMap.get(tankId).getRefuels().add(refuel);
        }
        return returnMap;
    }
}
