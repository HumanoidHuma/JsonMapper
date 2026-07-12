package my.entities;

public sealed interface JsonValue permits JsonString, JsonNumber, JsonNull, JsonArray, JsonBoolean, JsonObject {
}