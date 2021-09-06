package hometask_3.ObjectMapper;

public class Entity {
    private String entityName;

    @FieldName("ag")
    private Integer ageTest;

    private String age;

    public Entity(String entityName, Integer ageTest, String age) {
        this.entityName = entityName;
        this.ageTest = ageTest;
        this.age = age;
    }
}
