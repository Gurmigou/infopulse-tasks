package hometask_2.Task_1_singleton;

public class A {
    public void goo() {
        System.out.println("A: " + Singleton.getInstance());
        System.out.println("A: " + Singleton.getInstance());
    }
}
