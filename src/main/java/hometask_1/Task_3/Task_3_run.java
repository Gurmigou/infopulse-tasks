package hometask_1.Task_3;

import java.util.List;
import java.util.NoSuchElementException;

public class Task_3_run {
    private final User DEFAULT_USER = new User("Sasha", 0, null);
    private final Database database;

    public Task_3_run(Database database) {
        this.database = database;
    }

    public static void main(String[] args) {
        var app = new Task_3_run(new Database());
        var database = app.database;

        // ex. 1
        database.findUserById(1)
                .orElseThrow(NoSuchElementException::new);

        var user1 = database.findUserById(1)
                                  .orElse(app.DEFAULT_USER);

        var user2 = database.findUserById(1)
                                  .orElseGet(() -> new User("Petya", 0, null));

        // ex. 2
        double averageBalance = database.findAllUsers()
                                        .stream()
                                        .flatMap(List::stream)
                                        .distinct()
                                        .peek(user -> user.setBalance(user.getBalance() * 1.1))
                                        .mapToDouble(User::getBalance)
                                        .average()
                                        .orElseThrow(NoSuchElementException::new);

        // ex. 3
        database.findUsersFiltered(user -> user.getBalance() > 15009)
                .ifPresentOrElse(list -> list.forEach(user -> user.setBalance(user.getBalance() * 0.95)),
                                 () -> System.out.println("There are no users."));
    }
}
