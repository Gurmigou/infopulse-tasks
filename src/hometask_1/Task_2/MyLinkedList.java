package hometask_1.Task_2;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;
    private int modCount;

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean add(T value) {
        return addLast(value);
    }

    public boolean addFirst(T value) {
        if (isEmpty())
            addWhenListIsEmpty(value);
        else {
            Node<T> node = new Node<>(value, null, this.head);
            head.prev = node;
            this.head = node;
        }
        size++;
        modCount++;
        return true;
    }

    public boolean addLast(T value) {
        if (isEmpty())
            addWhenListIsEmpty(value);
        else {
            Node<T> node = new Node<>(value, this.tail, null);
            tail.next = node;
            this.tail = node;
        }
        size++;
        modCount++;
        return true;
    }

    private void addWhenListIsEmpty(T value) {
        Node<T> node = new Node<>(value, null, null);
        this.head = node;
        this.tail = node;
    }

    public T removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        if (head == tail)
            return removeWhenHeadAndTailAreEqual();

        Node<T> oldHead = head;
        this.head = head.next;
        head.prev = null;
        size--;
        modCount++;

        return oldHead.value;
    }

    public T removeLast() {
        if (tail == null)
            throw new NoSuchElementException();
        if (head == tail)
            return removeWhenHeadAndTailAreEqual();

        Node<T> oldTail = tail;
        this.tail = tail.prev;
        tail.next = null;
        size--;
        modCount++;

        return oldTail.value;
    }

    private T removeWhenHeadAndTailAreEqual() {
        T value = head.value;
        head = tail = null;
        size = 0;
        modCount++;
        return value;
    }

    private T removeNode(Node<T> node) {
        if (node == head)
            return removeFirst();
        if (node == tail)
            return removeLast();

        Node<T> prev = node.prev;
        prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        modCount++;

        return node.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIteratorImpl();
    }

    public ListIterator<T> listIterator() {
        return new ListIteratorImpl();
    }

    private final class ListIteratorImpl implements ListIterator<T> {
        private Node<T> cur = head;
        private Node<T> lastReturned;
        private int index;
//        private final int constSize = size;
        private int expectedModCount = modCount;

        // Methods "next()" and "previous()" set this field to true;
        private boolean curIndexCanBeUsed = false;

        private void checkIfBothModify() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            checkIfBothModify();
            if (!hasNext())
                throw new NoSuchElementException();

            T value = cur.value;
            lastReturned = cur;
            cur = cur.next;
            index++;
            curIndexCanBeUsed = true;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            checkIfBothModify();
            if (!hasPrevious())
                throw new NoSuchElementException();

            T value;
            if (cur == null) {
                value = lastReturned.value;
                cur = lastReturned;
            }
            else {
                value = cur.prev.value;
                cur = cur.prev;
                lastReturned = cur;
            }
            index--;
            curIndexCanBeUsed = true;
            return value;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            checkIfBothModify();
            if (lastReturned == null || !curIndexCanBeUsed)
                throw new IllegalStateException("No elements have been read yet");
            removeNode(lastReturned);
            curIndexCanBeUsed = false;
            expectedModCount++;
        }

        @Override
        public void set(T e) {
            checkIfBothModify();
            if (lastReturned == null || !curIndexCanBeUsed)
                throw new IllegalStateException("No elements have been read yet");
            lastReturned.value = e;
        }

        @Override
        public void add(T e) {
            checkIfBothModify();

            if (index == 0) {
                addFirst(e);
                index = 1;
            }
            else if (lastReturned == tail)
                addLast(e);
            else {
                Node<T> node = new Node<>(e, lastReturned, cur);
                lastReturned.next = node;
                lastReturned = lastReturned.next;
                cur.prev = node;
                modCount++;
                size++;
                index++;
            }

            expectedModCount++;
        }
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";

        var sb = new StringBuilder("[");
        for (T t : this) {
            sb.append(t.toString())
              .append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');

        return sb.toString();
    }
}
