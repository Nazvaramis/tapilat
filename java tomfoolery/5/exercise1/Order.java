package isp.lab4.exercise1;

import java.time.LocalDateTime;
import java.util.*;

public class Order {


    //  am creagt lista de arrayu , fara asta nu putem
    // sa bagam produse in lista
    public ArrayList<Product> product = new ArrayList<Product>();

    public void addProduct(Product p){


        product.add(p);


    }
    private String orderID;

    private double totalPrice;


    private LocalDateTime data;

    public Order(String orderID, double totalPrice){
        this.orderID = orderID;
        this.totalPrice = totalPrice;


    }
    private Order(LocalDateTime data){

        this.data = data;
    }
     private LocalDateTime getdata(){

        return data;
     }



}
