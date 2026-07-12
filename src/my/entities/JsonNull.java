package my.entities;

public final class JsonNull implements JsonValue {
    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {}

    public JsonNull value() {
        return INSTANCE;
    }
}