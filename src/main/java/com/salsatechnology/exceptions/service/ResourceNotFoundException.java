package com.salsatechnology.exceptions.service;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String username){
        super("Product Orders with username " + username + " not found" );
    }
}
