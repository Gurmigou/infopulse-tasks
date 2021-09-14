package hometask_1.Task_3;

import java.util.List;
import java.util.Optional;

public class User {
    private String name;
    private double balance;
    private List<User> friends;

    public User(String name, double balance, List<User> friends) {
        this.name = name;
        this.balance = balance;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Optional<List<User>> getFriends() {
        return Optional.ofNullable(friends);
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
