package org.example.lec02;

public class Car {
    public String name;
    private String price;
    public String engType;
    public int engPower;
    public int maxSpeed;
    public Car(String name) {this.name=name; engType="DOHC 2.4L"; engPower=137;
        maxSpeed=190; price="Не доступно!";}
    @Override
    public String toString() {return "Car {name = " + name + '}';
    }

    public String getPrice() {return price;}
    private void setPrice(String newPrice) {price = newPrice;}


}
