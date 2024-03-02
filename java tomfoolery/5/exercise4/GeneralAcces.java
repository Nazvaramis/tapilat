package isp.lab4.exercise4;

public class GeneralAcces implements Ticket {






    // Event info
    private String name;

    private String email;
    private int age;
    private String eventName;
    private String date;
    private int seatNumber;
    private double price;


    public GeneralAcces(String name, int age, String email, double price , int SeatNumber, String date , String eventname)
    {
        this.name = name;   //
        this.age = age;
        this.email = email; //
        this.price = price; //
        this.seatNumber = SeatNumber; //
        this.eventName = eventname;  //
        this.date = date;   //

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

        this.price = price;
        try {
            if (price <= 0) {
                throw new IllegalArgumentException("Value of the ticket can't be 0 or less");

            }


            System.out.println("Thank you for buying General Ticket");
        }
        catch(IllegalArgumentException e){

            System.out.println(e.getMessage());
        }


    }


    public void setAge(int age){

        this.age = age;
    }

    public int getAge(){

        return age;
    }

    public String getName(){

        return name;
    }
    public void setName(String name){

        this.name = name;
    }
    public void setEmail(String email){

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

    public String getEmail(){

        return email;
    }



    public String toString() {
        return "     \nGeneral Access Ticket \n \n" +
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







