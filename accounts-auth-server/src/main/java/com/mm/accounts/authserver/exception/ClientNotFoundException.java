package com.mm.accounts.authserver.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}
