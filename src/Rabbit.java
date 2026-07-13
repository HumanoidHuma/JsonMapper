import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rabbit {
    public String name;
    public Integer age;
    private String secretMessage = "THIS IS A SECREAT";

    public Box<?> box = null;

    public Boolean isThatAnimal = true;

    public List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8);

    public Object nullObject = null;

    public char symbol = 'a';

    public int[] array = new int[]{11, 22, 33};

    public Map<String, Integer> table = new HashMap<>();

    public Rabbit(String name, Integer age) {
        this(name, age, null);
    }

    public Rabbit(String name, Integer age, Box<?> box) {
        this.name = name;
        this.age = age;
        this.box = box;

        table.put("Key1", 100);
        table.put("Ley2", 200);
        table.put("Key3", 300);
    }
}
