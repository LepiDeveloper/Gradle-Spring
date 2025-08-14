package be.kdg.programming3.spaceMissions.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseError(DataAccessException ex, Model model) {
        logger.error("Database error occurred: {}", ex.getMessage(), ex);
        model.addAttribute("errorMessage", "A database error occurred. Please try again later.");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error/databaseError";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Exception ex, Model model) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        model.addAttribute("errorMessage", "Something went wrong. Please contact support.");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error/generalError";
    }

}
