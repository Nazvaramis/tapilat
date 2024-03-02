package isp.lab4.exercise4;

public class VIPTicket implements Ticket {


    private String name;

    private String email;
    private int age;
    private String eventName;
    private String date;
    private int seatNumber;
    private double price;

    private int choice;

    private VIPTicket(String eventName, String date, int seatNumber, double price, String name, String email) {
        this.eventName = eventName;
        this.date = date;
        this.seatNumber = seatNumber;
        this.price = price;
        this.name = name;
        this.email = email;

    }

    public VIPTicket(int choice){
        this.choice = choice;

    }
    public VIPTicket(){

    }

    // Implement the Ticket interface methods
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {


        try {
            if (price <= 0) {
                throw new IllegalArgumentException("Value of the ticket can't be 0 or less");

            }

            this.price = price;
            System.out.println("Thank you for buying VIP Ticket");
        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }


    }


    public void setAge(int age) {

        this.age = age;
    }

    public int getAge() {

        return age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setEmail(String email) {

        this.email = email;

    }

    public boolean checkTicket() {
        try {
            if (name == null || email == null || age == 0 || eventName == null || date == null || seatNumber == 0 || price == 0) {
                throw new NullPointerException("Name and email can't be null");


            }

            if (age < 18) {
                throw new IllegalArgumentException("You can't enter if you are under 18 ");

            }

            if (price <= 0) {

                throw new IllegalArgumentException("You cant have the price less than 0 ");

            }
            return true;
        }
        catch(NullPointerException | IllegalArgumentException e){
            System.out.println("Ticket invalid" + e.getMessage());
            return false;
        }

        finally {
            System.out.println("Checking complete");
        }

    }



    public String getEmail() {

        return email;
    }


    public String toString() {
        return "     \nVIP Ticket \n \n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Age: " + age + "\n" +
                "Event name: " + eventName + "\n" +
                "Date: " + date + "\n" +
                "Seat number: " + seatNumber + "\n" +
                "Price: " + price + "\n";
    }

    public String viewTicketInfo(String name, String email, String eventName, int seatNumber, double price)
    {
    return this.toString();
    }


}




