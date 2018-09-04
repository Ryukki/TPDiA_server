package logics;

import entities.*;

<<<<<<< HEAD
import java.util.Date;
=======
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265
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
<<<<<<< HEAD
    private double[] tmp;
=======
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265

    public SumBlock(HashMap<Integer, Tank> tankHashMap){
        this.tankHashMap = tankHashMap;
        tank = new Tank();
        pistol = new Pistol();
<<<<<<< HEAD
        tmp = new double[4];
=======
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265
    }

    public void saveValues(List<BaseClass> groupedBlock){
        for(BaseClass baseClass: groupedBlock){

            if(tankHashMap.containsKey(baseClass.getTankId())){
                tank = tankHashMap.get(baseClass.getTankId());
            }
            else{
                tank = new Tank();
<<<<<<< HEAD
                if(baseClass instanceof TankMeasure){
                    tank.setTempCounter(((TankMeasure)baseClass).getFuelVolume());  //ustawienie początkowej wartości ilości paliwa
                }
=======
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265
            }

            if(baseClass instanceof TankMeasure){
                tank.setId(((TankMeasure)baseClass).getTankId());
                tank.setCurrentFuelVolume(((TankMeasure)baseClass).getFuelVolume());
                tankHashMap.put(tank.getId(), tank);
<<<<<<< HEAD
                tmp[tank.getId()-1] = tank.getCurrentFuelVolume();
=======
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265
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
<<<<<<< HEAD
            else if(baseClass instanceof Refuel){
                tank = tankHashMap.get(baseClass.getTankId());
                tank.setVolumeAfterRefuel(((Refuel)baseClass).getFuelVolume() + tank.getCurrentFuelVolume());
                tank.setTempCounter(((Refuel)baseClass).getFuelVolume() + tank.getTempCounter());
                System.out.println("Napełniony zbiornik nr: " + tank.getId());
            }
        }
        generateReport(groupedBlock.get(groupedBlock.size()-1).getMeasureDate());
    }

    public void generateReport(Date measureDate){
        double temp = 0.0;

        System.out.println("Raport na chwilę " + measureDate.toString());

=======
        }

        generateReport();
    }

    private void generateReport(){
        double temp = 0.0;

>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265
        for(Map.Entry<Integer, Tank> tankEntry : tankHashMap.entrySet()) {
            Integer key = tankEntry.getKey();
            Tank tank = tankEntry.getValue();
            HashMap<Integer, Pistol> pistols = tank.getPistols();

            for(Map.Entry<Integer, Pistol> pistolEntry : pistols.entrySet()) {
                Pistol pistol = pistolEntry.getValue();
                temp += pistol.getPumpedOutVolume();
            }

            tank.setPumpedOutVolume(temp);
<<<<<<< HEAD
            tank.setBalance(Math.abs(tank.getTempCounter() - tank.getCurrentFuelVolume() - tank.getPumpedOutVolume()));

            System.out.println("Paliwo wypompowane ze zbiornika nr "+ tank.getId() + " : "+ tank.getPumpedOutVolume());
            System.out.println("Przebieg zbiornika "+ tank.getId() + " : "+ tank.getTempCounter());
            System.out.println("W zbiorniku nr "+ tank.getId() + " pozostała ilość paliwa: "+ tank.getCurrentFuelVolume());
            System.out.println("Wartość bilansu: " + tank.getBalance());

=======
            System.out.println("Fuel pumped out from tank "+ tank.getId() + " : "+ tank.getPumpedOutVolume());
>>>>>>> 4b9027ea12d42c61654a8c4570d96c33bd544265
            temp = 0.0;
        }
    }

}
