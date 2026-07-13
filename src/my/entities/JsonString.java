package my.entities;

public final class JsonString implements JsonValue {
    private final String value;

    public JsonString(String value) {
        if (value == null) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    public String value() {
        return value;
    }
}