package com.esprit.tn.candidacy_microservice.exception;

public class StudentException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public StudentException(String message) {
        super(message);
    }
}
