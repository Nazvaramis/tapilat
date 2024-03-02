package isp.lab4.exercise2;

public class Exercise2 {


public static void main(String args[]){


Laptop l = new Laptop(65);
l.charge(35);

System.out.println("\n\n\n");

SmartPhone s = new SmartPhone(55);
s.charge(45);


}




}

/*  cv exemplu din labu 4 de pe teams
public class Person { private String name;

    private Address adr; public Person(String n, Address a){name=n;adr=a;}

    public void display(){

        System.out.println("Name="+name);

    } public static void main(String[] args){

        Address a = new Address("streetX",15);

        Person p1 = new Person("Alin",a);

        Employee p2 = new Employee("Jon", new Address("Street mare",142),2000);

        p1.display();



 */