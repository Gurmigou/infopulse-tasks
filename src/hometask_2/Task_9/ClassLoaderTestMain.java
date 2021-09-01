package hometask_2.Task_9;
import java.util.Random;

public class ClassLoaderTestMain {

    public static void main(String[] args) {
        System.out.println("String classloader: " + String.class.getClassLoader());
        System.out.println("Random classloader: " + Random.class.getClassLoader());
        System.out.println("My class classloader: " + ClassLoaderTestMain.class.getClassLoader());
    }
}
