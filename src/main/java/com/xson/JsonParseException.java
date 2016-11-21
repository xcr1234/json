package com.xson;


public class JsonParseException extends RuntimeException{
    private static final long serialVersionUID = -4662221914777717339L;

    public JsonParseException() {
    }

    public JsonParseException(String message) {
        super(message);
    }

    public JsonParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonParseException(Throwable cause) {
        super(cause);
    }
}
