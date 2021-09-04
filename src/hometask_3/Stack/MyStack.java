package hometask_3.Stack;

public class MyStack<E> {
    Node<E> top = null;

    private int size;

    private static class Node<E> {
        Node<E> prev;
        E value;

        public Node(Node<E> prev, E value) {
            this.prev = prev;
            this.value = value;
        }
    }

    public void push(E value) {
        top = new Node<>(top, value);
        size++;
    }

    public E pop() {
        E toReturn = top.value;
        top = top.prev;
        size--;
        return toReturn;
    }

    public E peek() {
        return (top == null) ? null : top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
