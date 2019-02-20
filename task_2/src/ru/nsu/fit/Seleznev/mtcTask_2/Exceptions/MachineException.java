package ru.nsu.fit.Seleznev.mtcTask_2.Exceptions;

public class MachineException extends Exception {
    private String errMessage;

    public MachineException(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
