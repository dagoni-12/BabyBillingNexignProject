package ru.anger.CRM.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BrtException extends RuntimeException {
    private final HttpStatus status;
    private final String body;

    public HttpStatus getStatus() { return status; }
    public String getBody() { return body; }
}

