package logics;

import entities.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kacper on 2018-09-04.
 */
public class SumBlock {
    private HashMap<Integer, Tank> tankHashMap;
    private Tank tank;
    private Pistol pistol;
    private HashMap<Integer, Pistol> pistolsMap;
    private double[] tmp;

    public SumBlock(HashMap<Integer, Tank> tankHashMap){
        this.tankHashMap = tankHashMap;
        tank = new Tank();
        pistol = new Pistol();
        tmp = new double[4];
    }

    public void saveValues(List<BaseClass> groupedBlock){
        for(BaseClass baseClass: groupedBlock){

            if(tankHashMap.containsKey(baseClass.getTankId())){
                tank = tankHashMap.get(baseClass.getTankId());
            }
            else{
                tank = new Tank();
                if(baseClass instanceof TankMeasure){
                    tank.setTempCounter(((TankMeasure)baseClass).getFuelVolume());  //ustawienie początkowej wartości ilości paliwa
                }
            }

            if(baseClass instanceof TankMeasure){
                tank.setId(((TankMeasure)baseClass).getTankId());
                tank.setCurrentFuelVolume(((TankMeasure)baseClass).getFuelVolume());
                tankHashMap.put(tank.getId(), tank);
                tmp[tank.getId()-1] = tank.getCurrentFuelVolume();
            }
            else if(baseClass instanceof NozzleMeasure){
                tank.setId(((NozzleMeasure)baseClass).getTankId());
                pistolsMap = tank.getPistols();
                if(pistolsMap.containsKey(((NozzleMeasure)baseClass).getPistolId())){
                    pistol = pistolsMap.get(((NozzleMeasure)baseClass).getPistolId());
                }
                else{
                    pistol = new Pistol();
                }

                pistol.setPumpedOutVolume(((NozzleMeasure)baseClass).getTotalCounter() + ((NozzleMeasure)baseClass).getLiterCounter());
                pistolsMap.put(((NozzleMeasure)baseClass).getPistolId(), pistol);
                tank.setPistols(pistolsMap);
            }
            else if(baseClass instanceof Refuel){
                tank = tankHashMap.get(baseClass.getTankId());
                tank.setVolumeAfterRefuel(((Refuel)baseClass).getFuelVolume() + tank.getCurrentFuelVolume());
                tank.setTempCounter(((Refuel)baseClass).getFuelVolume() + tank.getTempCounter());
            }
        }
        generateReport(groupedBlock.get(groupedBlock.size()-1).getMeasureDate());
    }

    public void generateReport(Date measureDate){
        double temp = 0.0;

        System.out.println("Raport na chwilę " + measureDate.toString());

        for(Map.Entry<Integer, Tank> tankEntry : tankHashMap.entrySet()) {
            Integer key = tankEntry.getKey();
            Tank tank = tankEntry.getValue();
            HashMap<Integer, Pistol> pistols = tank.getPistols();

            for(Map.Entry<Integer, Pistol> pistolEntry : pistols.entrySet()) {
                Pistol pistol = pistolEntry.getValue();
                temp += pistol.getPumpedOutVolume();
            }

            tank.setPumpedOutVolume(temp);
            tank.setBalance((tank.getTempCounter() - tank.getCurrentFuelVolume()) - tank.getPumpedOutVolume());

            System.out.println("Paliwo wypompowane ze zbiornika nr "+ tank.getId() + " : "+ tank.getPumpedOutVolume());
            System.out.println("Przebieg zbiornika "+ tank.getId() + " : "+ tank.getTempCounter());
            System.out.println("W zbiorniku nr "+ tank.getId() + " pozostała ilość paliwa: "+ tank.getCurrentFuelVolume());
            System.out.println("Wartość bilansu: " + tank.getBalance());

            temp = 0.0;
        }
    }

}
