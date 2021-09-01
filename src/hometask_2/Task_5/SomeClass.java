package hometask_2.Task_5;

public class SomeClass {
    private String name;

    public SomeClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String secretMethod(String message, int n) {
        var sb = new StringBuilder();

        for (int i = 0; i < n; i++)
            sb.append(message).append('\n');

        return sb.toString();
    }
}
