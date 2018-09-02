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
            NozzleMeasure singleNozzleMeasure;
            List<TankMeasure> tankMeasures;
            TankMeasure singleTankMeasure;
            List<Refuel> refuelList;
            Refuel singleRefuel;

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

                        switch (dataHeader){

                            case "tankMeasure":
                                System.out.println("receiving tank measures...");
                                tankMeasures  = new ArrayList<>();
                                singleTankMeasure = null;
                                tankMeasures= (ArrayList<TankMeasure>)fromClient.readObject();
                                singleTankMeasure = tankMeasures.get(0);
                                System.out.println(singleTankMeasure.getMeasureDate());
                                DataGrouper dataGrouper = new DataGrouper();
                                TankMeasure[] groupedBlock = dataGrouper.createGroupedDataBlockTankMeasures(tankMeasures);
                                MaskModule maskModule = new MaskModule();
                                maskModule.getDividedDataBlock(new ArrayList<>(Arrays.asList(groupedBlock)));

                                break;

                            case "refuel":
                                System.out.println("receiving refuel data...");
                                refuelList = new ArrayList<>();
                                singleRefuel = null;
                                refuelList= (ArrayList<Refuel>)fromClient.readObject();
                                singleRefuel = refuelList.get(0);
                                System.out.println(singleRefuel.getMeasureDate());
                                break;

                            case "nozzleMeasure":
                                System.out.println("receiving nozzle measures...");
                                nozzleMeasures  = new ArrayList<>();
                                singleNozzleMeasure = null;
                                nozzleMeasures= (ArrayList<NozzleMeasure>)fromClient.readObject();
                                singleNozzleMeasure = nozzleMeasures.get(0);
                                System.out.println(singleNozzleMeasure.getMeasureDate());
                                break;
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
}
