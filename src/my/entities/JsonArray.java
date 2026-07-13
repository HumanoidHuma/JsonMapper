package my.entities;

public final class JsonArray implements JsonValue {
    private final Iterable<JsonValue> elements;

    public JsonArray(Iterable<JsonValue> value) {
        this.elements = value;
    }

    public Iterable<JsonValue> value() {
        return elements;
    }
}