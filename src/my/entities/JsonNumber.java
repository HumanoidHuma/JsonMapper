package my.entities;

public final class JsonNumber implements JsonValue {
    private final Number value;

    public JsonNumber(Number value) {
        this.value = value;
    }

    public Number value() {
        return value;
    }
}