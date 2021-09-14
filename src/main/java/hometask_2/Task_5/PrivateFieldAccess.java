package hometask_2.Task_5;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrivateFieldAccess {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        changePrivateFieldValue();

        invokePrivateMethod();
    }

    static void invokePrivateMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var object = new SomeClass("Reflection API");
        Class<?> clazz = object.getClass();

        Method method = clazz.getDeclaredMethod("secretMethod", String.class, int.class);
        method.setAccessible(true);

        Object result = method.invoke(object, "Secret method was invoked 5 times", 5);
        System.out.println(result);
    }

    static void changePrivateFieldValue() throws NoSuchFieldException, IllegalAccessException {
        var object = new SomeClass("Reflection API");
        Class<?> clazz = object.getClass();

        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);

        System.out.println("Before reflection usage: " + object.getName());

        field.set(object, "NEW VALUE SET USING REFLECTION API");

        System.out.println("After reflection usage: " + object.getName());
    }
}
