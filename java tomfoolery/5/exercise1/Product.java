package isp.lab4.exercise1;

public class Product {

    private Customer c;


    private String productID;

    private String name;

    private int products;

    private double price;

//array de products idk 10
// probabil customer ia o variabila din product ca si un thread
// adica un ticket , ai 10 tickete(conectiune) si gen floseste un ticket
// cand un customer da browse la produs ( probabil idk)


    ProductCategory productCategory;


    public Product(String productID,String name,double price){

        this.productID = productID;
        this.name = name;
        this.price = price;

    }







}
