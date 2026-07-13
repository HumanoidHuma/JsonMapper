package my.entities;

public final class JsonBoolean implements JsonValue {
    private static final JsonBoolean TRUE = new JsonBoolean(Boolean.TRUE);
    private static final JsonBoolean FALSE = new JsonBoolean(Boolean.FALSE);

    private final Boolean value;

    public JsonBoolean(Boolean value) {
        this.value = value;
    }

    public static JsonBoolean of(boolean value) {
        return value ? TRUE : FALSE;
    }

    public Boolean value() {
        return value;
    }
}