package isp.lab4.exercise4;

public interface Ticket {

public String getDate();

public void setDate(String date);


public int getSeatNumber();

public void setSeatNumber(int SeatNumber);

public double getPrice();

public void setEventName(String eventName);

public void setPrice(double price);




public void setAge(int age);


// get personal info

    public String getName();
    public void setName(String name);

    public String getEventName();

    public void setEmail(String email);
    public String getEmail();



    public int getAge();



    // TICKET DISPLAYING INFO PROMP COMMAND

public String viewTicketInfo(String name , String email, String eventName,int seatNumber,double price);





}
