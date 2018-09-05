import entities.*;
import logics.DataGrouper;
import logics.SumBlock;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Tank> tankHashMap;
        int port = 6868;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            AllMeasures allMeasures;
            Map<Integer, AllMeasures> allMeasuresDividedByTank;
            Map<Integer, Future<Tank>> tankFutureMap = new HashMap<>();
            ExecutorService executor = Executors.newCachedThreadPool();

            tankHashMap = new HashMap<>();
            DataGrouper dataGrouper = new DataGrouper();

            String dataHeader;

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
                            allMeasuresDividedByTank = dataGrouper.groupMeasuresByTankId(allMeasures);
                            for (Map.Entry<Integer, AllMeasures> dividedMeasure: allMeasuresDividedByTank.entrySet()){
                                Integer tankId = dividedMeasure.getKey();
                                Future<Tank> tankFuture;
                                if(!tankFutureMap.containsKey(tankId)){
                                    tankFuture = executor.submit(new SumBlock(tankHashMap.get(tankId), dividedMeasure.getValue()));
                                    tankFutureMap.put(tankId, tankFuture);
                                }else {
                                    tankFuture = tankFutureMap.get(tankId);
                                    tankHashMap.put(tankId, tankFuture.get());
                                    tankFuture = executor.submit(new  SumBlock(tankHashMap.get(tankId), dividedMeasure.getValue()));
                                    tankFutureMap.put(tankId, tankFuture);
                                }
                            }
                        }
                        }
                    catch (EOFException exc) {
                        break;
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
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
}
