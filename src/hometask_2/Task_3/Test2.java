package hometask_2.Task_3;

import java.util.Comparator;

public class Test2 implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return 0;
    }

    /*
    // Bridge method
    public int compare(Object o1, Object o2) {
        return compare((Integer) o1, (Integer) o2);
    }
    */
}
