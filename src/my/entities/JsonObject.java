package my.entities;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class JsonObject implements JsonValue {
    private final Map<String, JsonValue> members;

    public JsonObject(Map<String, JsonValue> members) {
        this.members = Collections.unmodifiableMap(new LinkedHashMap<>(members));
    }

    public JsonObject() {
        members = new LinkedHashMap<>();
    }

    public Map<String, JsonValue> members() {
        return members;
    }

    public void addMember(String key, JsonValue value) {
        members.put(key, value);
    }
}