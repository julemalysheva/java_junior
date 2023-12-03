package org.example.lec01;

import java.util.Arrays;
import java.util.List;

public class UserMain {
    public static void main(String[] args) {
        List<User> list = Arrays.asList(new User("Pavel", 25), new User("Oleg", 40), new User("Ivan", 35));

        list.stream().map(user -> new User(user.name, user.age - 5)).filter(user -> user.age <= 25).forEach(System.out::println);

    }
}
