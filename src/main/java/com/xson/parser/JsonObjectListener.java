package com.xson.parser;


import com.xson.JsonArray;
import com.xson.JsonObject;
import com.xson.JsonParseException;
import com.xson.feature.DeserializeFeature;
import com.xson.feature.SerializeFeature;
import com.xson.lexer.JSONBaseListener;
import com.xson.lexer.JSONParser;
import com.xson.util.StringUtil;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.List;

public class JsonObjectListener extends JSONBaseListener{

    protected ParseTreeProperty<Object> property = new ParseTreeProperty<Object>();
    protected ParseTreeProperty<JsonObject> jsonObjectParseTreeProperty = new ParseTreeProperty<JsonObject>();
    protected ParseTreeProperty<JsonArray> jsonArrayParseTreeProperty = new ParseTreeProperty<JsonArray>();
    private JsonObject jsonObject;
    private DeserializeFeature feature;

    public JsonObjectListener(DeserializeFeature feature) {
        this.feature = feature;
    }

    @Override
    public void enterJsonArray(JSONParser.JsonArrayContext ctx) {
        throw new JsonParseException("need JsonObject but meet JsonArray!");
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    @Override
    public void exitJsonObject(JSONParser.JsonObjectContext ctx) {
        this.jsonObject = jsonObjectParseTreeProperty.get(ctx.object());
    }

    @Override
    public void enterObject(JSONParser.ObjectContext ctx) {
        jsonObjectParseTreeProperty.put(ctx,new JsonObject());
    }

    @Override
    public void enterArray(JSONParser.ArrayContext ctx) {
        jsonArrayParseTreeProperty.put(ctx,new JsonArray());
    }

    @Override
    public void exitPair(JSONParser.PairContext ctx) {
        JsonObject jsonObject = jsonObjectParseTreeProperty.get(ctx.getParent());

        String name = StringUtil.parseJson(ctx.STRING().getText(),feature);
        JSONParser.ValueContext valueContext = ctx.value();
        Object value = property.get(valueContext);

        if(value!=null||feature.readNullValue()){
            jsonObject.put(name,value);
        }
    }



    @Override
    public void exitString(JSONParser.StringContext ctx) {
        property.put(ctx, StringUtil.parseJson(ctx.STRING().getText(),feature));
    }

    @Override
    public void exitNUMBER(JSONParser.NUMBERContext ctx) {
        String numberString = ctx.NUMBER().getText();
        property.put(ctx, feature.numberCaster().toNumber(numberString));
    }

    @Override
    public void exitArray(JSONParser.ArrayContext ctx) {
        JsonArray jsonArray = jsonArrayParseTreeProperty.get(ctx);
        List<JSONParser.ValueContext> valueContexts = ctx.value();
        for(JSONParser.ValueContext valueContext:valueContexts){
            jsonArray.add(property.get(valueContext));
        }
    }

    @Override
    public void exitObjectValue(JSONParser.ObjectValueContext ctx) {
        JsonObject jsonObject = jsonObjectParseTreeProperty.get(ctx.object());
        property.put(ctx,jsonObject);
    }

    @Override
    public void exitArrayValue(JSONParser.ArrayValueContext ctx) {
        JsonArray jsonArray = jsonArrayParseTreeProperty.get(ctx.array());
        property.put(ctx,jsonArray);
    }

    @Override
    public void exitBOOLEANTRUE(JSONParser.BOOLEANTRUEContext ctx) {
        property.put(ctx,true);
    }

    @Override
    public void exitBOOLEANFALSE(JSONParser.BOOLEANFALSEContext ctx) {
        property.put(ctx,false);
    }

    @Override
    public void exitNULL(JSONParser.NULLContext ctx) {
        property.put(ctx,null);
    }
}
