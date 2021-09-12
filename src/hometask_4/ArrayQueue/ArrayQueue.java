package hometask_4.ArrayQueue;

public class ArrayQueue<E> {
    private E[] queue;
    private static final int MIN_QUEUE_LENGTH = 16;

    private int front;
    private int end;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        this.queue = (E[]) new Object[MIN_QUEUE_LENGTH];
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int queueLength) {
        if (queueLength <= 0)
            throw new IllegalArgumentException("stackLength must be a positive number");

        this.queue = (E[]) new Object[queueLength];
    }

    private void leftShiftIntoArray(E[] arr) {
        int left = 0;
        int right = front;

        while (right < queue.length)
            arr[left++] = queue[right++];

        int size = size();
        front = 0;
        end = size;
    }

    private void grow(int newLength) {
        if (newLength <= MIN_QUEUE_LENGTH)
            throw new IllegalArgumentException("Illegal \"newLength\" value");

        @SuppressWarnings("unchecked")
        E[] newQueue = (E[]) new Object[newLength];

        leftShiftIntoArray(newQueue);
        this.queue = newQueue;
    }

    private void ensureCapacity() {
        if (end == queue.length) {
            // if the queue is half empty
            if (front > queue.length / 2)
                leftShiftIntoArray(queue);
            else  {
                // grow depending on queue size
                if (queue.length <= MIN_QUEUE_LENGTH)
                    grow(queue.length * (2 + (int) Math.ceil((double)MIN_QUEUE_LENGTH / queue.length)));
                else
                    grow(queue.length * 2);
            }
        }
    }

    public void push(E value) {
        ensureCapacity();
        queue[end++] = value;
    }

    public E pop() {
        return (front < queue.length) ? queue[front++] : null;
    }

    public E peek() {
        return queue[front];
    }

    public int size() {
        return end - front;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}