package logics;

import entities.BaseClass;
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
    private Map<Integer, Integer> tablicaIndeksow;

    private void createStatistics(List<BaseClass> dataList){
        tablicaLiczebnosci = new HashMap<>();
        tablicaPozycji = new ArrayList<>();
        tablicaIndeksow = new HashMap<>();

        for(BaseClass baseClass: dataList){
            Integer licznik = 0;
            Integer id = baseClass.getTankId();
            if(!tablicaLiczebnosci.containsKey(id)){
                tablicaLiczebnosci.put(id, licznik);
            }else{
                licznik = tablicaLiczebnosci.get(id);
                tablicaLiczebnosci.put(id, ++licznik);
            }
            tablicaPozycji.add(licznik);
        }
        int index = 0;
        for(BaseClass baseClass: dataList){
            Integer id = baseClass.getTankId();
            if(!tablicaIndeksow.containsKey(id)){
                tablicaIndeksow.put(id, index);
                index += tablicaLiczebnosci.get(id) + 1;
            }
        }
    }

    public BaseClass[] createGroupedDataBlock(List<BaseClass> dataList){
        createStatistics(dataList);
        BaseClass[] groupedDataBlock = new BaseClass[dataList.size()];
        for(int i = 0; i < dataList.size(); i++){
            BaseClass baseClass = dataList.get(i);
            int newIndex = tablicaIndeksow.get(baseClass.getTankId()) + tablicaPozycji.get(i);
            groupedDataBlock[newIndex] = dataList.get(i);
        }

        return groupedDataBlock;
    }

    public Map<Integer, Integer> getTablicaLiczebnosci() {
        return tablicaLiczebnosci;
    }

    public List<Integer> getTablicaPozycji() {
        return tablicaPozycji;
    }

    public Map<Integer, Integer> getTablicaIndeksow() {
        return tablicaIndeksow;
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
