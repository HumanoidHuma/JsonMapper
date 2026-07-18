package my.mapper;

import my.deserializers.JsonValueDeserializer;
import my.entities.*;
import my.serializers.JsonValueSerializer;

public class ReflectionJsonValueMapper implements JsonValueMapper {
    private final JsonValueSerializer serializer;
    private final JsonValueDeserializer deserializer;

    public ReflectionJsonValueMapper(JsonValueSerializer serializer, JsonValueDeserializer deserializer) {
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    @Override
    public JsonValue toJsonValue(Object value) {
        return serializer.toJsonValue(value);
    }

    @Override
    public <T> T fromJsonValue(JsonValue value, Class<T> targetType) {
        return deserializer.fromJsonValue(value, targetType);
    }
}
