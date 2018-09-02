import entities.BaseClass;
import entities.NozzleMeasure;
import entities.Refuel;
import entities.TankMeasure;
import logics.DataGrouper;
import logics.MaskModule;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        int port = 6868;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            List<NozzleMeasure> nozzleMeasures;
            List<TankMeasure> tankMeasures;
            List<Refuel> refuelList;
            List<BaseClass> genericList;

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
                        genericList = new ArrayList<>();
                        switch (dataHeader){
                            case "tankMeasure":
                                System.out.println("receiving tank measures...");
                                tankMeasures= (ArrayList<TankMeasure>)fromClient.readObject();
                                for(TankMeasure tankMeasure: tankMeasures){
                                    genericList.add((BaseClass) tankMeasure);
                                }
                                break;

                            case "refuel":
                                System.out.println("receiving refuel data...");
                                refuelList= (ArrayList<Refuel>)fromClient.readObject();
                                for(Refuel refuel: refuelList){
                                    genericList.add((BaseClass)refuel);
                                }
                                break;

                            case "nozzleMeasure":
                                System.out.println("receiving nozzle measures...");
                                nozzleMeasures= (ArrayList<NozzleMeasure>)fromClient.readObject();
                                for (NozzleMeasure nozzleMeasure: nozzleMeasures){
                                    genericList.add((BaseClass)nozzleMeasure);
                                }
                                break;
                        }
                        DataGrouper dataGrouper = new DataGrouper();
                        BaseClass[] groupedBlock = dataGrouper.createGroupedDataBlock(genericList);
                        MaskModule maskModule = new MaskModule();
                        maskModule.getDividedDataBlock(new ArrayList<>(Arrays.asList(groupedBlock)));


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
}
