package my.writers;

import my.entities.*;

import java.util.Iterator;
import java.util.Map;

public class CompactJsonWriter implements JsonWriter {
    @Override
    public String write(JsonValue value) {
        StringBuilder sb = new StringBuilder();

        append(value, sb);

        return sb.toString();
    }

    public void append(JsonValue json, StringBuilder sb) {
        if (json instanceof JsonNull nullValue) {
            append(nullValue, sb);
        } else if (json instanceof JsonString stringValue) {
            append(stringValue, sb);
        } else if (json instanceof JsonNumber numberValue) {
            append(numberValue, sb);
        } else if (json instanceof JsonBoolean booleanValue) {
            append(booleanValue, sb);
        } else if (json instanceof JsonArray arrayValue) {
            append(arrayValue, sb);
        } else if (json instanceof JsonObject objectValue) {
            append(objectValue, sb);
        }
    }

    public void append(JsonString json, StringBuilder sb) {
        sb.append("\"").append(json.value()).append("\"");
    }

    public void append(JsonNull json, StringBuilder sb) {
        sb.append("null");
    }

    public void append(JsonNumber json, StringBuilder sb) {
        sb.append(json.value());
    }

    public void append(JsonBoolean json, StringBuilder sb) {
        sb.append(json.value());
    }

    public void append(JsonArray json, StringBuilder sb) {
        sb.append("[");
        Iterator<JsonValue> it = json.value().iterator();
        while (it.hasNext()) {
            append(it.next(), sb);
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("]");
    }

    public void append(JsonObject json, StringBuilder sb) {
        sb.append("{");
        Map<JsonString, JsonValue> members = json.members();
        int count = 0;
        int size = members.size();
        for (JsonString key : members.keySet()) {
            append(key, sb);
            sb.append(":");
            append(members.get(key), sb);
            if (count < size - 1) {
                sb.append(",");
            }
            count++;
        }
        sb.append("}");
    }
}
