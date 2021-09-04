package hometask_3.ObjectMapper;

public class Entity {
    private String entityName;

    @ColumnName("ag")
    private Integer age;

    public Entity(String entityName, Integer age) {
        this.entityName = entityName;
        this.age = age;
    }
}
