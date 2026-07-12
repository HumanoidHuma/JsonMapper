package my.mapper;

import my.deserializers.JsonValueDeserializer;
import my.entities.*;
import my.serializers.JsonValueSerializer;

public class ReflectionJsonValueMapper implements JsonValueMapper {
    private final JsonValueSerializer serializer;
    private final JsonValueDeserializer decoder;

    public ReflectionJsonValueMapper(JsonValueSerializer serializer, JsonValueDeserializer decoder) {
        this.serializer = serializer;
        this.decoder = decoder;
    }

    @Override
    public JsonValue toJsonValue(Object value) {
        return serializer.toJsonValue(value);
    }

    @Override
    public <T> T fromJsonValue(JsonValue value, Class<T> targetType) {
        return decoder.fromJsonValue(value, targetType);
    }
}
