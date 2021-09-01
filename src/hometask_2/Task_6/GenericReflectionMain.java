package hometask_2.Task_6;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class GenericReflectionMain {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        genericClassReflection(List.of(1, 2, 3));
    }

    static void genericClassReflection(List<Integer> list) {
        System.out.println(list.getClass().getGenericSuperclass());
        System.out.println(((ParameterizedType) list
                .getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    static void genericMethodReturnTypeReflection(Class<?> clazz, String methodName, Class<?>... params)
            throws NoSuchMethodException
    {
        Method method = clazz.getMethod(methodName, params);
        Type returnType = method.getGenericReturnType();

        if (returnType instanceof ParameterizedType type) {
            Type[] arguments = type.getActualTypeArguments();

            Arrays.stream(arguments)
                  .forEach(t -> System.out.println("type: " + t));
        }
    }

    static void genericMethodParamReflection(Class<?> clazz, String methodName, Class<?>... params) throws NoSuchMethodException {
        Method method = clazz.getMethod(methodName, params);

        Type[] parameterTypes = method.getGenericParameterTypes();

        Arrays.stream(parameterTypes)
                .filter(t -> t instanceof ParameterizedType)
                .map(t -> (ParameterizedType) t)
                .map(ParameterizedType::getActualTypeArguments)
                .flatMap(Arrays::stream)
                .forEach(t -> System.out.println("type: " + t));
    }

    static void genericFieldReflection(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        Type genericType = field.getGenericType();

        if (genericType instanceof ParameterizedType type) {
            Type[] fieldTypes = type.getActualTypeArguments();

            Arrays.stream(fieldTypes)
                  .forEach(t -> System.out.println("type: " + t));
        }
    }
}
