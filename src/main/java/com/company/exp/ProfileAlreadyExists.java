package com.company.exp;

public class ProfileAlreadyExists extends RuntimeException{
    public ProfileAlreadyExists(String message){
        super(message);
    }
}
