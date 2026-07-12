package my.writers;

import my.entities.JsonValue;

public interface JsonWriter {
    String write(JsonValue value);
}
