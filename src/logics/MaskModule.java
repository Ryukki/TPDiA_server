package logics;

import entities.TankMeasure;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.*;

/**
 * Created by Ryukki on 31.08.2018.
 */
public class MaskModule {
    private Map<Integer, ArrayList<Long>> tankMeasureDifferenceBlock;
    private RealMatrix IDMask;
    private RealMatrix timeMask;
    private int numberOfColumns;


    public void getDividedDataBlock(List<TankMeasure> groupedTankMeasureBlock){
        createTankMeasureDifferenceBlock(groupedTankMeasureBlock);
        generateIDMask();
    }

    private void createTankMeasureDifferenceBlock(List<TankMeasure> groupedTankMeasureBlock){
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

    private void generateIDMask(){
        double[][] matrixData;

        numberOfColumns = 0;
        int numberOfRows = tankMeasureDifferenceBlock.entrySet().size();
        for(Map.Entry<Integer, ArrayList<Long>> differences: tankMeasureDifferenceBlock.entrySet()){
            numberOfColumns+=differences.getValue().size();
        }
        matrixData = new double[numberOfRows][numberOfColumns];
        int row = 0, column = 0;
        for(Map.Entry<Integer, ArrayList<Long>> differences: tankMeasureDifferenceBlock.entrySet()){
            for(Long timeDifference: differences.getValue()){
                matrixData[row][column++] = 1;
            }
            row++;
        }

        IDMask = MatrixUtils.createRealMatrix(matrixData);
    }

    private void generateTimeMask(){
        double[][] matrixData;
        int numberOfRows = 0;

        matrixData = new double[numberOfRows][numberOfColumns];
        timeMask = MatrixUtils.createRealMatrix(matrixData);
    }
}
