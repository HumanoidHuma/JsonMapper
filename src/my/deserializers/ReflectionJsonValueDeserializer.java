package my.deserializers;

import my.entities.JsonValue;

public class ReflectionJsonValueDeserializer implements JsonValueDeserializer {

    public ReflectionJsonValueDeserializer() {}

    @Override
    public <T> T fromJsonValue(JsonValue value, Class<T> targetType) {
        throw new UnsupportedOperationException("Json parsing is not implemented yet");
    }
}
