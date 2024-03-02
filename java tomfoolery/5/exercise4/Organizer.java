package isp.lab4.exercise4;

public class Organizer {

GeneralAcces g = new GeneralAcces("Lebron james",15,"LebronJamal@ymail.com",50.0,35,"15.10.2022","Idk basketball convention or smth");


VIPTicket vip = new VIPTicket();

public Organizer(int choice){

    System.out.println("Please enter 1 for General Access ticket and 2 for VIP Ticket");

        switch(choice) {

        case 1:
        vip.setSeatNumber(15);
        vip.setPrice(150.0);
        vip.setDate("05.10.2022");
        vip.setEventName("Power puff fangirl club idk");
        vip.setEmail("optimusprime@ymail");
        vip.setAge(11);
        vip.setName("Zamfira");
        vip.getDate();
        vip.getPrice();
        vip.getEventName();
        vip.getEmail();
        vip.getName();
        vip.getAge();
        vip.getSeatNumber();


            vip.checkTicket();

        case 2:
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

        default:
            throw new IllegalArgumentException("Erorr! \n Please enter 1 for General Access and 2 for VIP Ticket" +choice);


    }
}





}

