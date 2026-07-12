package my.deserializers;

import my.entities.JsonValue;
import my.serializers.JsonValueSerializer;

public class ReflectionJsonValueDeserializer implements JsonValueDeserializer {

    public ReflectionJsonValueDeserializer() {}

    @Override
    public <T> T fromJsonValue(JsonValue value, Class<T> targetType) {
        return null;
    }
}
