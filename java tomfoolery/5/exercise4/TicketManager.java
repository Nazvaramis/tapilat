package isp.lab4.exercise4;

public class TicketManager {

    GeneralAcces g = new GeneralAcces("Lebron james",15,"LebronJamal@ymail.com",50.0,35,"15.10.2022","Idk basketball convention or smth");


    VIPTicket vip = new VIPTicket();

   public TicketManager(){

        vip.setSeatNumber(15);
        vip.setPrice(150.0);
        vip.setDate("05.10.2022");
        vip.setEventName("Power puff fangirl club idk");
        vip.setEmail("optimusprime@ymail");
        vip.setAge(19);
        vip.setName("Zamfira");
        vip.getDate();
        vip.getPrice();
        vip.getEventName();
        vip.getEmail();
        vip.getName();
        vip.getAge();
        vip.getSeatNumber();

        vip.checkTicket();
        vip.toString();

        g.setAge(18);
        g.setDate("25.09.2022");
        g.setEmail("Lebronjames@ymail.com");
        g.setSeatNumber(35);
        g.setPrice(50.0);
        g.setEventName("Beach Please");
        g.setName("Michael");
        g.getAge();
        g.getDate();
        g.getEmail();
        g.getName();
        g.getPrice();
        g.getEventName();
        g.getSeatNumber();

        g.checkTicket();
        g.toString();

    }



    public static void main(String args[]){


        TicketManager tm = new TicketManager();
        System.out.println(tm.vip.toString());
        System.out.println(tm.g.toString());

    }

}
