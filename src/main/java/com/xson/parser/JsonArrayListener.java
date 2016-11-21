package com.xson.parser;


import com.xson.JsonArray;
import com.xson.JsonParseException;
import com.xson.feature.DeserializeFeature;
import com.xson.lexer.JSONParser;

public class JsonArrayListener extends JsonObjectListener{

    private JsonArray jsonArray;

    public JsonArrayListener(DeserializeFeature feature) {
        super(feature);
    }

    public JsonArray getJsonArray() {
        return jsonArray;
    }

    @Override
    public void enterJsonArray(JSONParser.JsonArrayContext ctx) {

    }

    @Override
    public void exitJsonArray(JSONParser.JsonArrayContext ctx) {
        this.jsonArray = jsonArrayParseTreeProperty.get(ctx.array());
    }

    @Override
    public void enterJsonObject(JSONParser.JsonObjectContext ctx) {
        throw new JsonParseException("need JsonArray but meet JsonObject.");
    }
}
