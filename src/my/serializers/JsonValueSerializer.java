package my.serializers;

import my.entities.JsonValue;

public interface JsonValueSerializer {
    JsonValue toJsonValue(Object value);
}
