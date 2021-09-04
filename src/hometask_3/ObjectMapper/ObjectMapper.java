package hometask_3.ObjectMapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class ObjectMapper {
    private static <T> Field[] getPrivateFields(Class<T> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields)
            field.setAccessible(true);
        return declaredFields;
    }

    private static Optional<Field> findField(Field[] fields, Class<?> fieldType, String fieldName) {
        return Arrays.stream(fields)
                .filter(field -> field.getName().equals(fieldName) && field.getType().equals(fieldType))
                .findFirst();
    }

    private static <T, Q> void mapAssociatedFields(Field[] mapFromArr, Field[] mapToArr, T objFrom, Q objTo)
            throws NoSuchFieldException, IllegalAccessException
    {
        for (Field fromField : mapFromArr) {
            ColumnName annotation = fromField.getAnnotation(ColumnName.class);
            String columnName;

            if (annotation != null)
                columnName = annotation.value();
            else
                columnName = fromField.getName();

            Field toField = findField(mapToArr, fromField.getType(), columnName)
                    .orElseThrow(NoSuchFieldException::new);

            toField.set(objTo, fromField.get(objFrom));
        }
    }

    public static <T, Q> Q map(T pojo, Class<Q> dto) throws InstantiationException, IllegalAccessException,
                                                            NoSuchMethodException, InvocationTargetException,
                                                            NoSuchFieldException
    {
        Field[] pojoFields = getPrivateFields(pojo.getClass());
        Field[] dtoFields = getPrivateFields(dto);

        if (pojoFields.length != dtoFields.length)
            throw new IllegalStateException("Pojo class and DTO class have different number of fields");

        Q instance = dto.getDeclaredConstructor().newInstance();

        mapAssociatedFields(pojoFields, dtoFields, pojo, instance);

        return instance;
    }
}
