package com.deusleyDev.apiOficina.exceptions;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException(String message){
        super(message);
    }
}
