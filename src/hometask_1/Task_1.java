package hometask_1;

public class Task_1 {
    public static void main(String[] args) {
        int n = 5;
        System.out.println("Recursive: " + factorialRecursive(n));
        System.out.println("Iterative: " + factorialIterative(n));
    }

    static int factorialRecursive(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n must be >= 0");
        if (n == 0)
            return 1;
        return n * factorialRecursive(n - 1);
    }

    static int factorialIterative(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n must be >= 0");
        int result = 1;
        for (int i = n; i > 0; i--)
            result *= i;
        return result;
    }
}
