package hometask_4_and_5.ArrayStack;

public class ArrayStack<E> {
    private E[] stack;
    private int top;

    private static final int MIN_STACK_LENGTH = 16;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.stack = (E[]) new Object[MIN_STACK_LENGTH];
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int stackLength) {
        if (stackLength <= 0)
            throw new IllegalArgumentException("stackLength must be a positive number");

        this.stack = (E[]) new Object[stackLength];
    }

    private void grow(int newLength) {
        if (newLength <= MIN_STACK_LENGTH)
            throw new IllegalArgumentException("Illegal \"newLength\" value");

        @SuppressWarnings("unchecked")
        E[] newStack = (E[]) new Object[newLength];

        System.arraycopy(stack, 0, newStack, 0, stack.length);

        this.stack = newStack;
    }

    private void insureCapacity() {
        if (top == stack.length - 1) {
            if (stack.length <= MIN_STACK_LENGTH)
                grow(stack.length * (2 + (int) Math.ceil((double)MIN_STACK_LENGTH / stack.length)));
            else
                grow(stack.length * 2);
        }

    }

    public void push(E value) {
        insureCapacity();
        stack[top++] = value;
    }

    public E pop() {
        return (top == 0) ? null : stack[--top];
    }

    public E peek() {
        return (top == 0) ? null : stack[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }
}
