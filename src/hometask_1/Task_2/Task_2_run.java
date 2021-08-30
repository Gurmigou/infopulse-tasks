package hometask_1.Task_2;

public class Task_2_run {

    public static void main(String[] args) {
        var myList = new MyLinkedList<Integer>();

        for (int i = 0; i < 10; i++) {
            myList.add(i);
        }
        var iterator = myList.listIterator();

        iterator.next();
        iterator.next();

        iterator.add(99);

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
    }
}
