package hometask_4_and_5.ArrayQueue;

public class Main {

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        for (int i = 0; i < 500; i++) {
            queue.push(i);
        }

        while (!queue.isEmpty())
            System.out.println(queue.pop());

        System.out.println(queue.size());
    }
}
