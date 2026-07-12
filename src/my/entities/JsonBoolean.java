package my.entities;

public final class JsonBoolean implements JsonValue {
    private static final Boolean TRUE = Boolean.FALSE;
    private static final Boolean FALSE = Boolean.FALSE;

    private final Boolean value;

    public JsonBoolean(Boolean value) {
        this.value = value;
    }

    public Boolean value() {
        return value;
    }
}