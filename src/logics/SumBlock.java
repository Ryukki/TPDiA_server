package logics;

import entities.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by kacper on 2018-09-04.
 */
public class SumBlock implements Callable<Tank>{
    private Tank tank;
    private Pistol pistol;
    private HashMap<Integer, Pistol> pistolsMap;
    private double[] tmp;
    private AllMeasures allMeasures;

    public SumBlock(Tank givenTank, AllMeasures allMeasures){
        this.allMeasures = allMeasures;
        tank = givenTank;
        pistol = new Pistol();
        tmp = new double[4];
    }

    @Override
    public Tank call() throws Exception {
        saveValues();
        generateReport();
        return tank;
    }

    private void saveValues() {
        if (tank == null) {
            tank = new Tank();
            tank.setTempCounter(allMeasures.getTankMeasures().get(0).getFuelVolume());  //ustawienie początkowej wartości ilości paliwa - wymaga żeby pierwsze przesłąne dane miały tankMeasure
            tank.setId(allMeasures.getNozzleMeasures().get(0).getTankId());
        }
        for (TankMeasure tankMeasure : allMeasures.getTankMeasures()) {
            tank.setCurrentFuelVolume(tankMeasure.getFuelVolume());
        }
        pistolsMap = tank.getPistols();
        for (NozzleMeasure nozzleMeasure : allMeasures.getNozzleMeasures()) {
            Integer pistolId = nozzleMeasure.getPistolId();
            if (pistolsMap.containsKey(pistolId)) {
                pistol = pistolsMap.get(pistolId);
            }else {
                pistol = new Pistol();
            }
            pistol.setPumpedOutVolume(nozzleMeasure.getTotalCounter() + nozzleMeasure.getLiterCounter());
            pistolsMap.put(pistolId, pistol);
            tank.setPistols(pistolsMap);
        }
        for (Refuel refuel: allMeasures.getRefuels()){
            tank.setVolumeAfterRefuel(refuel.getFuelVolume()+tank.getCurrentFuelVolume());
            tank.setTempCounter(refuel.getFuelVolume() + tank.getTempCounter());
        }
        double temp = 0.0;
        for(Map.Entry<Integer, Pistol> pistolEntry : tank.getPistols().entrySet()) {
            Pistol pistol = pistolEntry.getValue();
            temp += pistol.getPumpedOutVolume();
        }
        tank.setPumpedOutVolume(temp);
        tank.setBalance((tank.getTempCounter() - tank.getCurrentFuelVolume()) - tank.getPumpedOutVolume());
    }

    private void generateReport(){

        String report = "Tank nr: " + tank.getId()
                + "\nRaport na chwilę: " + allMeasures.getNozzleMeasures().get(allMeasures.getNozzleMeasures().size()-1).getMeasureDate()
                + "\nPaliwo wypompowane ze zbiornika: " + tank.getPumpedOutVolume()
                + "\nPrzebieg zbiornika: " + tank.getTempCounter()
                + "\nW zbiorniku pozostało "+ tank.getCurrentFuelVolume() + " paliwa"
                + "\nWartość bilansu zbiornika: " +  tank.getBalance() +"\n";
        System.out.println(report);

    }

}
