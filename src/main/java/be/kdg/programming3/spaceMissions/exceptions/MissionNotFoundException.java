package be.kdg.programming3.spaceMissions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Mission not found")
public class MissionNotFoundException extends RuntimeException {

    public MissionNotFoundException(String message) {
        super(message);
    }

}
