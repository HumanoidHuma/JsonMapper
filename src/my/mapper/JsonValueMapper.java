package my.mapper;

import my.entities.JsonValue;

public interface JsonValueMapper {
    JsonValue toJsonValue(Object value);
    <T> T fromJsonValue(JsonValue value, Class<T> targetType);
}