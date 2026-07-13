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

    private void append(JsonValue json, StringBuilder sb) {
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

    private void appendEscapedString(String value, StringBuilder sb) {
        sb.append('"');

        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);

            switch (character) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\b' -> sb.append("\\b");
                case '\f' -> sb.append("\\f");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");

                default -> {
                    if (character < 0x20) {
                        sb.append(String.format("\\u%04x", (int) character));
                    } else {
                        sb.append(character);
                    }
                }
            }
        }

        sb.append('"');
    }

    private void append(JsonString json, StringBuilder sb) {
        appendEscapedString(json.value(), sb);
    }

    private void append(String value, StringBuilder sb) {
        appendEscapedString(value, sb);
    }

    private void append(JsonNull json, StringBuilder sb) {
        sb.append("null");
    }

    private void append(JsonNumber json, StringBuilder sb) {
        sb.append(json.value());
    }

    private void append(JsonBoolean json, StringBuilder sb) {
        sb.append(json.value());
    }

    private void append(JsonArray json, StringBuilder sb) {
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

    private void append(JsonObject json, StringBuilder sb) {
        sb.append("{");
        Iterator<Map.Entry<String, JsonValue>> it = json.members().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, JsonValue> entry = it.next();
            append(entry.getKey(), sb);
            sb.append(":");
            append(entry.getValue(), sb);
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("}");

//        sb.append("{");
//        Map<JsonString, JsonValue> members = json.members();
//        int count = 0;
//        int size = members.size();
//        for (JsonString key : members.keySet()) {
//            append(key, sb);
//            sb.append(":");
//            append(members.get(key), sb);
//            if (count < size - 1) {
//                sb.append(",");
//            }
//            count++;
//        }
//        sb.append("}");
    }
}
