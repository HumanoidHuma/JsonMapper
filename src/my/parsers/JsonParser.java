package my.parsers;

import my.entities.JsonValue;

public interface JsonParser {
    JsonValue parse(String json);
}
