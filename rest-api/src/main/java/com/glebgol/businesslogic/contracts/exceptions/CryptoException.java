package com.glebgol.businesslogic.contracts.exceptions;

public class CryptoException extends Exception {
    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
