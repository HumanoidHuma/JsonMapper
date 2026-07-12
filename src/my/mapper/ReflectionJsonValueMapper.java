package my.mapper;

import my.entities.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionJsonValueMapper implements JsonValueMapper {
    private static final Map<Class<?>, Field[]> fieldCache = new HashMap<>();

    @Override
    public JsonValue toJsonValue(Object value) {
        if (value == null) {
            return JsonNull.INSTANCE;
        } else if (value instanceof String stringValue) {
            return serializeString(stringValue);
        } else if (value instanceof Number numberValue) {
            return serializeNumber(numberValue);
        } else if (value instanceof Boolean booleanValue) {
            return serializeBoolean(booleanValue);
        } else if (value instanceof Iterable<?> iterableValue) {
            return serializeArray(iterableValue);
        }
        return serializeObject(value);
    }

    public static Field[] findSerializableFields(Class<?> clazz) {
        if (!fieldCache.containsKey(clazz)) {
            fieldCache.put(clazz, clazz.getFields());
        }
        return fieldCache.get(clazz);
    }

    public JsonNull serializeNull(Object value) {
        return JsonNull.INSTANCE;
    }

    public JsonString serializeString(String value) {
        return new JsonString(value);
    }

    public JsonNumber serializeNumber(Number value) {
        return new JsonNumber(value);
    }

    public JsonBoolean serializeBoolean(Boolean value) {
        return new JsonBoolean(value);
    }

    public JsonArray serializeArray(Iterable<?> value) {
        List<JsonValue> array = new ArrayList<>();
        for (Object arrayValue : array) {
            array.add(toJsonValue(arrayValue));
        }
        return new JsonArray(array);
    }

    public JsonObject serializeObject(Object value) {
        JsonObject result = new JsonObject();

        Class<?> clazz = value.getClass();
        Field[] fields = findSerializableFields(clazz);

        for (Field field : fields) {
            JsonString key = serializeString(field.getName());
            JsonValue jsonValue;
            try {
                jsonValue = toJsonValue(field.get(value));
            } catch (IllegalAccessException e) {
                System.out.println("Can not serialize \"" + field.getName() + "\" field");
                jsonValue = serializeNull(value);
            }
            result.addMember(key, jsonValue);
        }

        return result;
    }
}
