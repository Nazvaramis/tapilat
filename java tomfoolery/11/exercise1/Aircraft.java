package isp.lab10.exercise1;

public class Aircraft implements  Runnable{

    public int altitude;
    public String id;


    public Aircraft( String id){
        this.id=id;
    }

    public void receiveAtcCommand(AtcCommand cmd){

        if(cmd instanceof  TakeoffCommand){
            this.altitude = ((TakeoffCommand) cmd).altitude;
        }
        else if(cmd instanceof  LandCommand){
            this.altitude=((LandCommand) cmd).altitude;
        }

    }
    public void run(){

        System.out.println("On stand");
        // wait for TAKEOFF_CMD
        new TakeoffCommand(7000);
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Taxing");
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){}
        System.out.println("Taking off");
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){}
        System.out.println("Ascending");
        int currentAltitude=0;
        while(currentAltitude<altitude){
            currentAltitude+=1000;
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){}
        }

        System.out.println("Cruising");
        synchronized (this) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Descending");
        while(currentAltitude>altitude){
            currentAltitude-=1000;
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e){}
        }
        System.out.println("Landed");

    }

}
