package hometask_3.ObjectMapper;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity("test name", 99, "25");

        try {
            DTO mapped = ObjectMapper.map(entity, DTO.class);

            System.out.println(mapped.getEntityName());
            System.out.println(mapped.getag());
            System.out.println(mapped.getAge());

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
