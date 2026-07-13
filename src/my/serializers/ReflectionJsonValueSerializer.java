package my.serializers;

import my.entities.*;
import my.exceptions.JsonMappingException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectionJsonValueSerializer implements JsonValueSerializer {
    private final Map<Class<?>, Field[]> fieldCache = new ConcurrentHashMap<>();

    @Override
    public JsonValue toJsonValue(Object value) {
        if (value == null) {
            return JsonNull.INSTANCE;
        } else if (value instanceof String stringValue) {
            return serializeString(stringValue);
        } else if (value instanceof Number numberValue) {
            return serializeNumber(numberValue);
        } else if (value instanceof Boolean booleanValue) {
            return JsonBoolean.of(booleanValue);
        } else if (value instanceof Iterable<?> iterableValue) {
            return serializeIterable(iterableValue);
        } else if (value.getClass().isArray()) {
            return serializeArray(value);
        } else if (value instanceof Map<?, ?> mapValue) {
            return serializeMap(mapValue);
        } else if (value instanceof Character charValue) {
            return serializeString(charValue.toString());
        } else if (value instanceof Enum<?> enumValue) {
            return serializeString(enumValue.name());
        } else if (value instanceof JsonValue jsonValue) {
            return jsonValue;
        }
        return serializeObject(value);
    }

    private Field[] findSerializableFields(Class<?> clazz) {
        return fieldCache.computeIfAbsent(clazz, this::inspectSerializableFields);
    }

    private Field[] inspectSerializableFields(Class<?> clazz) {
        return clazz.getFields();
    }

    public JsonNull serializeNull(Object value) {
        return JsonNull.INSTANCE;
    }

    public JsonString serializeString(String value) {
        return new JsonString(value);
    }

    public JsonNumber serializeNumber(Number value) {
        if (value instanceof Double number && !Double.isFinite(number)) {
            throw new UnsupportedOperationException(
                    "JSON does not support non-finite number: " + number
            );
        }
        if (value instanceof Float number && !Float.isFinite(number)) {
            throw new UnsupportedOperationException(
                    "Json does not support non-finite number" + number
            );
        }

        return new JsonNumber(value);
    }

    public JsonBoolean serializeBoolean(Boolean value) {
        return JsonBoolean.of(value);
    }

    public JsonArray serializeIterable(Iterable<?> value) {
        List<JsonValue> array = new ArrayList<>();
        for (Object arrayValue : value) {
            array.add(toJsonValue(arrayValue));
        }
        return new JsonArray(array);
    }

    public JsonArray serializeArray(Object array) {
        int length = Array.getLength(array);
        List<JsonValue> elements = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            elements.add(toJsonValue(element));
        }

        return new JsonArray(elements);
    }

    public JsonObject serializeObject(Object value) {
        JsonObject result = new JsonObject();

        Class<?> clazz = value.getClass();
        Field[] fields = findSerializableFields(clazz);

        for (Field field : fields) {
            String key = field.getName();
            JsonValue jsonValue;
            try {
                jsonValue = toJsonValue(field.get(value));
            } catch (IllegalAccessException e) {
                // System.out.println("Can not serialize \"" + field.getName() + "\" field");
                throw new JsonMappingException(
                        "Can not read field '"
                        + field.getName()
                        + "' from"
                        + value.getClass().getName(),
                        e
                );
                // jsonValue = serializeNull(value);
            }
            result.addMember(key, jsonValue);
        }

        return result;
    }

    public JsonObject serializeMap(Map<?, ?> value) {
        Map<String, JsonValue> members = new LinkedHashMap<>();

        for (Map.Entry<?, ?> entry : value.entrySet()) {
            if (!(entry.getKey() instanceof String key)) {
                throw new UnsupportedOperationException(
                        "JSON object keys must be strings, but found: "
                        + entry.getKey().getClass().getName()
                );
            }

            members.put(key, toJsonValue(entry.getValue()));
        }

        return new JsonObject(members);
    }
}
