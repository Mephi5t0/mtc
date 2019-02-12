package ru.nsu.fit.Seleznev.mtcProject.Exceptions;

public class ParseException extends Exception{
    private String reason;

    public ParseException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
