package com.xson;

public class JsonCastException extends RuntimeException {
    private static final long serialVersionUID = 5610214602143275196L;

    public JsonCastException() {
    }

    public JsonCastException(String message) {
        super(message);
    }

    public JsonCastException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonCastException(Throwable cause) {
        super(cause);
    }
}
