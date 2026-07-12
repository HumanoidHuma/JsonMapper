package my;

import my.deserializers.ReflectionJsonValueDeserializer;
import my.mapper.JsonValueMapper;
import my.mapper.ReflectionJsonValueMapper;
import my.parsers.JsonParser;
import my.parsers.RecursiveJsonParser;
import my.serializers.ReflectionJsonValueSerializer;
import my.writers.CompactJsonWriter;
import my.writers.JsonWriter;

public class JsonCodec {
    private final JsonValueMapper mapper;
    private final JsonWriter writer;
    private final JsonParser parser;

    public JsonCodec(JsonValueMapper mapper, JsonWriter writer, JsonParser parser) {
        this.mapper = mapper;
        this.writer = writer;
        this.parser = parser;
    }

    public JsonCodec() {
        mapper = new ReflectionJsonValueMapper(new ReflectionJsonValueSerializer(), new ReflectionJsonValueDeserializer());
        writer = new CompactJsonWriter();
        parser = new RecursiveJsonParser();
    }

    public String toJson(Object object) {
        return writer.write(mapper.toJsonValue(object));
    }

    public <T> T fromJson(String json, Class<T> targetType) {
        return mapper.fromJsonValue(parser.parse(json), targetType);
    }
}
