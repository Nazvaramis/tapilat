package isp.lab10.exercise1;

import java.util.ArrayList;
import java.util.List;

public class ATC {

    List<Aircraft> aircraftList= new ArrayList();


    public void addAirCraft(String id){

        Aircraft aircraft=new Aircraft(id);
        aircraftList.add((aircraft));
        new Thread(aircraft).start();

    }

    public void sendCommand(String aircraftId,AtcCommand cmd){
        aircraftList.forEach((a)-> {
            if(a.id.equals(aircraftId)){
                a.receiveAtcCommand(cmd);
                synchronized (a){
                    a.notify();
                }
            }
        });

    }

}
