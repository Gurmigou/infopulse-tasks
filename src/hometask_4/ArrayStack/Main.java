package hometask_4.ArrayStack;

public class Main {
    public static void main(String[] args) {

        ArrayStack<Integer> stack = new ArrayStack<>(5);

        for (int i = 0; i < 800; i++) {
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
