package com.campusconnect.campusconnect.exceptions;

public class RegistrationException extends RuntimeException {
    public RegistrationException(String message) { super(message); }
    public RegistrationException(String message, Throwable cause) { super(message, cause); }
}
