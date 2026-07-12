import java.util.ArrayList;
import java.util.List;

public class Rabbit {
    public String name;
    public Integer age;
    private String secretMessage = "THIS IS A SECREAT";

    public Box<?> box = null;

    public Boolean isThatAnimal = true;

    public List<Integer> array = List.of(1, 2, 3, 4, 5, 6, 7, 8);

    public Object nullObject = null;

    public Rabbit(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Rabbit(String name, Integer age, Box<?> box) {
        this.name = name;
        this.age = age;
        this.box = box;
    }
}
