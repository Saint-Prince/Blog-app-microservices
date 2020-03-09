package com.sakinramazan.microservices.postservice.exception;

import lombok.Value;

import java.util.Date;

// It doesnt need to be changed, make it immutable
@Value
public final class ExceptionResponse {
    private final Date timestamp;
    private final String message;
    private final String detail;
}
