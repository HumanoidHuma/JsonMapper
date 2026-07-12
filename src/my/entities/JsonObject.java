package my.entities;

import java.util.LinkedHashMap;
import java.util.Map;

public final class JsonObject implements JsonValue {
    private final Map<JsonString, JsonValue> members;

    public JsonObject(Map<JsonString, JsonValue> members) {
        this.members = members;
    }

    public JsonObject() {
        members = new LinkedHashMap<>();
    }

    public Map<JsonString, JsonValue> members() {
        return members;
    }

    public void addMember(JsonString key, JsonValue value) {
        members.put(key, value);
    }
}