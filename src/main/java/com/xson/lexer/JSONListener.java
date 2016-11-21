// Generated from Y:/mvn/json/src/main/resources\JSON.g4 by ANTLR 4.5.3
package com.xson.lexer;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JSONParser}.
 */
public interface JSONListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code JsonObject}
	 * labeled alternative in {@link JSONParser#json}.
	 * @param ctx the parse tree
	 */
	void enterJsonObject(JSONParser.JsonObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JsonObject}
	 * labeled alternative in {@link JSONParser#json}.
	 * @param ctx the parse tree
	 */
	void exitJsonObject(JSONParser.JsonObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JsonArray}
	 * labeled alternative in {@link JSONParser#json}.
	 * @param ctx the parse tree
	 */
	void enterJsonArray(JSONParser.JsonArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JsonArray}
	 * labeled alternative in {@link JSONParser#json}.
	 * @param ctx the parse tree
	 */
	void exitJsonArray(JSONParser.JsonArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(JSONParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(JSONParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(JSONParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(JSONParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link JSONParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(JSONParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link JSONParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(JSONParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterString(JSONParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitString(JSONParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NUMBER}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNUMBER(JSONParser.NUMBERContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NUMBER}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNUMBER(JSONParser.NUMBERContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjectValue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterObjectValue(JSONParser.ObjectValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjectValue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitObjectValue(JSONParser.ObjectValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayValue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterArrayValue(JSONParser.ArrayValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayValue}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitArrayValue(JSONParser.ArrayValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BOOLEANTRUE}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBOOLEANTRUE(JSONParser.BOOLEANTRUEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BOOLEANTRUE}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBOOLEANTRUE(JSONParser.BOOLEANTRUEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BOOLEANFALSE}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterBOOLEANFALSE(JSONParser.BOOLEANFALSEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BOOLEANFALSE}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitBOOLEANFALSE(JSONParser.BOOLEANFALSEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NULL}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNULL(JSONParser.NULLContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NULL}
	 * labeled alternative in {@link JSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNULL(JSONParser.NULLContext ctx);
}