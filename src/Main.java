import entities.*;
import logics.DataGrouper;
import logics.SumBlock;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {

    static HashMap<Integer, Tank> tankHashMap;

    public static void main(String[] args) {
        int port = 6868;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            AllMeasures allMeasures;

            String dataHeader;
            boolean firstFunctionalMeasures = false;

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                for (;;) {
                    try {
                        dataHeader = (String)fromClient.readObject();
                        if(dataHeader.equals("allMeasures")){
                            allMeasures = (AllMeasures)fromClient.readObject();
                            if(firstFunctionalMeasures){
                                processAllMeasures(allMeasures);
                            }else if (!allMeasures.getTankMeasures().isEmpty()){
                                firstFunctionalMeasures=true;
                                processAllMeasures(allMeasures);
                            }


                        }
                        }
                    catch (EOFException exc) {
                        break;
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void processAllMeasures(AllMeasures allMeasures){
        Map<Integer, AllMeasures> allMeasuresDividedByTank;
        Map<Integer, Future<Tank>> tankFutureMap = new HashMap<>();
        ExecutorService executor = Executors.newCachedThreadPool();

        tankHashMap = new HashMap<>();
        DataGrouper dataGrouper = new DataGrouper();
        allMeasuresDividedByTank = dataGrouper.groupMeasuresByTankId(allMeasures);
        for (Map.Entry<Integer, AllMeasures> dividedMeasure: allMeasuresDividedByTank.entrySet()){
            Integer tankId = dividedMeasure.getKey();
            Future<Tank> tankFuture;
            if(!tankFutureMap.containsKey(tankId)){
                tankFuture = executor.submit(new SumBlock(tankHashMap.get(tankId), dividedMeasure.getValue()));
                tankFutureMap.put(tankId, tankFuture);
            }else {
                tankFuture = tankFutureMap.get(tankId);
                try {
                    tankHashMap.put(tankId, tankFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                tankFuture = executor.submit(new  SumBlock(tankHashMap.get(tankId), dividedMeasure.getValue()));
                tankFutureMap.put(tankId, tankFuture);
            }
        }
    }
}
