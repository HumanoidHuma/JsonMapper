package my.deserializers;

import my.entities.JsonValue;

public interface JsonValueDeserializer {
    <T> T fromJsonValue(JsonValue value, Class<T> targetType);
}
