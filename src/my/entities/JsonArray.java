package my.entities;

public final class JsonArray implements JsonValue {
    private final Iterable<JsonValue> value;

    public JsonArray(Iterable<JsonValue> value) {
        this.value = value;
    }

    public Iterable<JsonValue> value() {
        return value;
    }
}