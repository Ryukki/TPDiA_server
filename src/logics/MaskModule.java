package logics;

import entities.TankMeasure;

import java.time.Duration;
import java.util.*;

/**
 * Created by Ryukki on 31.08.2018.
 */
public class MaskModule {
    private Map<Integer, ArrayList<Long>> tankMeasureDifferenceBlock;
    private Integer[] IDMask;
    private Integer[] timeMask;

    private void createTankMeasureDifferenceBlock(List<TankMeasure> groupedTankMeasureBlock, Map<Integer, Integer> tablicaLiczebnosci){
        tankMeasureDifferenceBlock = new HashMap<>();
        int id = -1;
        Date lastDate = null;
        ArrayList<Long> tempArray = new ArrayList<>();
        for(TankMeasure tankMeasure: groupedTankMeasureBlock){
            if(id!=tankMeasure.getTankId()){
                if(!tempArray.isEmpty()){
                    tankMeasureDifferenceBlock.put(id, tempArray);
                }
                id = tankMeasure.getTankId();
                tempArray = new ArrayList<>();
                lastDate = tankMeasure.getMeasureDate();

            }else {
                Long dateDifference = Math.abs(tankMeasure.getMeasureDate().getTime() - lastDate.getTime());
                tempArray.add(dateDifference);
            }
        }
        tankMeasureDifferenceBlock.put(id, tempArray);
    }

    public void getDividedDataBlock(List<TankMeasure> groupedTankMeasureBlock, Map<Integer, Integer> tablicaLiczebnosci){
        createTankMeasureDifferenceBlock(groupedTankMeasureBlock, tablicaLiczebnosci);
    }
}
