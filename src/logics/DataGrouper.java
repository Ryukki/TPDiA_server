package logics;

import entities.TankMeasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ryukki on 17.08.2018.
 */
public class DataGrouper {

    private Map<Integer, Integer> tablicaLiczebnosci;
    private List<Integer> tablicaPozycji;
    private Map<Integer, Integer> tablicaIndeksowTankMeasures;

    private void createTankStatistics(List<TankMeasure> tankMeasureList){
        tablicaLiczebnosci = new HashMap<>();
        tablicaPozycji = new ArrayList<>();
        tablicaIndeksowTankMeasures = new HashMap<>();

        for(TankMeasure tankMeasure: tankMeasureList){
            Integer licznik = 0;
            Integer id = tankMeasure.getTankId();
            if(!tablicaLiczebnosci.containsKey(id)){
                tablicaLiczebnosci.put(id, licznik);
            }else{
                licznik = tablicaLiczebnosci.get(id);
                tablicaLiczebnosci.put(id, ++licznik);
            }
            tablicaPozycji.add(licznik);
        }
        int index = 0;
        for(TankMeasure tankMeasure: tankMeasureList){
            Integer id = tankMeasure.getTankId();
            if(!tablicaIndeksowTankMeasures.containsKey(id)){
                tablicaIndeksowTankMeasures.put(id, index);
                index += tablicaLiczebnosci.get(id) + 1;
            }
        }
    }

    public TankMeasure[] createGroupedDataBlockTankMeasures(List<TankMeasure> tankMeasureList){
        createTankStatistics(tankMeasureList);
        TankMeasure[] groupedDataBlock = new TankMeasure[tankMeasureList.size()];
        for(int i = 0; i < tankMeasureList.size(); i++){
            TankMeasure tankMeasure = tankMeasureList.get(i);
            int newIndex = tablicaIndeksowTankMeasures.get(tankMeasure.getTankId()) + tablicaPozycji.get(i);
            groupedDataBlock[newIndex] = tankMeasureList.get(i);
        }

        return groupedDataBlock;
    }

    public Map<Integer, Integer> getTablicaLiczebnosci() {
        return tablicaLiczebnosci;
    }

    public List<Integer> getTablicaPozycji() {
        return tablicaPozycji;
    }

    public Map<Integer, Integer> getTablicaIndeksowTankMeasures() {
        return tablicaIndeksowTankMeasures;
    }
}
/**
 * blok danych (3) - jeden z wszyskimi typami danych, czy osobny dla każdego
 *
 * Tablica pozycji (6) zawiera tyle elementów, ile rekordów znajduje się w bloku danych (3), a jej elementy to kolejne wartości liczników wystąpień konkretnych wartości ID na kolejnych pozycjach w bloku danych (3).
 * czyli każdy element ma liczniki każdego ID, czy w danym elemencie jest nowa wartość licznika ID na danej pozycji
 *
 * Tablica indeksów (7) zawiera tyle elementów, ile
 * występuje różnych wartości ID w danych, a jej elementy to początkowe elementy w
 * docelowym, tj. pogrupowanym bloku danych (9)
 */
