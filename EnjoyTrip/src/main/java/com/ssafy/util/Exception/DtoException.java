package com.ssafy.util.Exception;

public class DtoException extends Exception {

    public DtoException(Class<?> clazz, String msg) {
        super(createMsg(clazz, msg));
    }

    private static String createMsg(Class<?> clazz, String msg) {
        String className = clazz.getName();
        return className + ": " + msg;
    }
}