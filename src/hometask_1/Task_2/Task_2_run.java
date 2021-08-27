package hometask_1.Task_2;
import java.util.LinkedList;
import java.util.ListIterator;

public class Task_2_run {

    public static void main(String[] args) {
//        var list = new LinkedList<Integer>();
//        for (int i = 0; i < 5; i++) {
//            list.add(i);
//        }
//
//        ListIterator<Integer> iterator = list.listIterator();
//
//        iterator.next();
//        iterator.next();
//
//        iterator.add(99);
//        iterator.add(100);
//        iterator.add(101);
//        iterator.set(99);
//        System.out.println(list);



        var myList = new LinkedList<Integer>();

        for (int i = 0; i < 10; i++) {
            myList.add(i);
        }
        var iterator = myList.listIterator();
//        while (iterator.hasNext())
//            System.out.println(iterator.next());
//
//        System.out.println("-----------------");
//
//        while (iterator.hasPrevious()) {
//            iterator.previous();
//            iterator.remove();
//            System.out.println(myList);
//        }

        iterator.next();
        iterator.next();

        iterator.add(99);
//        iterator.add(99);
//        iterator.add(99);

        System.out.println(myList);

        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.previous());
        System.out.println(iterator.previous());
        System.out.println(iterator.previous());

        iterator.add(99);
        iterator.add(99);
        iterator.add(99);

        System.out.println(myList);

        System.out.println(iterator.next());
        System.out.println(iterator.next());

//        while (iterator.hasNext())
//            System.out.println(iterator.next());
//
//        System.out.println("-----------------");
//        while (iterator.hasPrevious()) {
//            iterator.previous();
//            iterator.remove();
//            System.out.println(myList);
//        }

    }
}
