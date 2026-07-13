package my.deserializers;

import my.entities.JsonValue;

public class RecursiveJsonValueDeserializer implements JsonValueDeserializer {

    public RecursiveJsonValueDeserializer() {}

    @Override
    public <T> T fromJsonValue(JsonValue value, Class<T> targetType) {
        throw new UnsupportedOperationException("Json parsing is not implemented yet");
    }
}
