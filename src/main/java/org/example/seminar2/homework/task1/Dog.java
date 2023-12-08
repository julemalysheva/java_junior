package org.example.seminar2.homework.task1;

public class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    public void makeSound() {
        System.out.println("Гав");
    }
}
