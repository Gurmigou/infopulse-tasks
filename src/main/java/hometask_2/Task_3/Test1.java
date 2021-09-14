package hometask_2.Task_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Test1 {
    public static void main(String[] args) {
        Serializable s = foo();
        System.out.println(s);
    }

    public static <G extends ArrayList<String> & Serializable> G foo() {
        return null;
    }
}
