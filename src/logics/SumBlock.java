package logics;

import entities.*;

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

    public SumBlock(HashMap<Integer, Tank> tankHashMap){
        this.tankHashMap = tankHashMap;
        tank = new Tank();
        pistol = new Pistol();
    }

    public void saveValues(List<BaseClass> groupedBlock){
        for(BaseClass baseClass: groupedBlock){

            if(tankHashMap.containsKey(baseClass.getTankId())){
                tank = tankHashMap.get(baseClass.getTankId());
            }
            else{
                tank = new Tank();
            }

            if(baseClass instanceof TankMeasure){
                tank.setId(((TankMeasure)baseClass).getTankId());
                tank.setCurrentFuelVolume(((TankMeasure)baseClass).getFuelVolume());
                tankHashMap.put(tank.getId(), tank);
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
        }

        generateReport();
    }

    private void generateReport(){
        double temp = 0.0;

        for(Map.Entry<Integer, Tank> tankEntry : tankHashMap.entrySet()) {
            Integer key = tankEntry.getKey();
            Tank tank = tankEntry.getValue();
            HashMap<Integer, Pistol> pistols = tank.getPistols();

            for(Map.Entry<Integer, Pistol> pistolEntry : pistols.entrySet()) {
                Pistol pistol = pistolEntry.getValue();
                temp += pistol.getPumpedOutVolume();
            }

            tank.setPumpedOutVolume(temp);
            System.out.println("Fuel pumped out from tank "+ tank.getId() + " : "+ tank.getPumpedOutVolume());
            temp = 0.0;
        }
    }

}
