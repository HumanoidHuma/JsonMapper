package my.entities;

public final class JsonString implements JsonValue {
    private final String value;

    public JsonString(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}