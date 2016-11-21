package com.xson.parser;

import com.xson.lexer.JSONBaseListener;
import com.xson.lexer.JSONLexer;
import com.xson.lexer.JSONParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

public class JsonValueParser {

    public static JSONLexer initLexer(InputStream inputStream) throws IOException{
        return new JSONLexer(new ANTLRInputStream(inputStream));
    }

    public static JSONLexer initLexer(String str){
        return new JSONLexer(new ANTLRInputStream(str));
    }

    public static JSONParser initParser(JSONLexer lexer){
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        return new JSONParser(commonTokenStream);
    }

    public static void walk(JSONParser parser, JSONBaseListener listener){
        ParseTree tree = parser.json();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener,tree);
    }
}
