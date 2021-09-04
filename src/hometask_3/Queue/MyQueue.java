package hometask_3.Queue;

public class MyQueue<E> {
    Node<E> head = null;
    Node<E> tail = null;

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
        final Node<E> queueNode = new Node<>(null, value);
        if (head == null)
            head = queueNode;
        else
            tail.prev = queueNode;

        tail = queueNode;
        size++;
    }

    public E pop() {
        final Node<E> prevHead = head;
        if (head == tail)
            head = tail = null;
        else
            head = head.prev;

        size--;
        return prevHead.value;
    }

    public E peek() {
        final Node<E> first = head;
        return (first == null) ? null : first.value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}