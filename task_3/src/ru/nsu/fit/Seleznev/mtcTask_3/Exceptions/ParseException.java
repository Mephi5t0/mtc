package ru.nsu.fit.Seleznev.mtcTask_3.Exceptions;

public class ParseException extends Exception{
    private String errMessage;

    public ParseException(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
