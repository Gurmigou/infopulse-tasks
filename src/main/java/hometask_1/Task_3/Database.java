package hometask_1.Task_3;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Database {
    public Optional<User> findUserById(int id) {
        // find user by id using sql
        return Optional.of(new User("Vasya", 15000, null));
    }

    public Optional<List<User>> findAllUsers() {
        return Optional.of(List.of(new User("Vasya", 15000, null),
                                   new User("Petya", 9500, null)));
    }

    public Optional<List<User>> findUsersFiltered(Predicate<? super User> filter) {
        // ...
        return Optional.empty(); // just for test
    }
}
