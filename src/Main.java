import my.JsonCodec;

public class Main {
    public static void main(String[] args) {
        JsonCodec jsonCodec = new JsonCodec();

        Rabbit rabbit = new Rabbit("Piter", 10);
        String json = jsonCodec.toJson(rabbit);
        System.out.println(json);

        Box<String> box = new Box<>("Present");
        Rabbit rabbit2 = new Rabbit("Masha", 2, box);
        json = jsonCodec.toJson(rabbit2);
        System.out.println(json);
    }
}