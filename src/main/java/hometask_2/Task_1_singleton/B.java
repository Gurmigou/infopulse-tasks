package hometask_2.Task_1_singleton;

public class B {
    public void foo() {
        System.out.println("B: " + Singleton.getInstance());
        System.out.println("B: " + Singleton.getInstance());
    }
}
