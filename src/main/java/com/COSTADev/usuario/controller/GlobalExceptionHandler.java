package com.COSTADev.usuario.controller;

import com.COSTADev.usuario.infrasctruture.exceptions.ConflictException;
import com.COSTADev.usuario.infrasctruture.exceptions.IllegalArgumentException;
import com.COSTADev.usuario.infrasctruture.exceptions.ResourceNotFoundException;
import com.COSTADev.usuario.infrasctruture.exceptions.UnauthorizedException;
import com.COSTADev.usuario.infrasctruture.exceptions.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerResourceNotFoundException(ResourceNotFoundException ex,
                                                                             HttpServletRequest Request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildError(HttpStatus.NO_CONTENT.value(),
                ex.getMessage(),
                Request.getRequestURI(),
                "Not Found"
        ));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handlerConflictionException(ConflictException ex,
                                                                        HttpServletRequest Request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildError(HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                Request.getRequestURI(),
                "Not Found"
        ));
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handlerUnhathorizedException(UnauthorizedException ex,
                                                               HttpServletRequest Request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(buildError(HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                Request.getRequestURI(),
                "Not Found"
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handlerIllegalArgumentException(IllegalArgumentException ex,
                                                                  HttpServletRequest Request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildError(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                Request.getRequestURI(),
                "Not Found"
        ));
    }

    private ErrorResponseDTO buildError(int status, String message, String path, String error) {
        return ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .error(error)
                .status(status)
                .path(path)
                .build();
    }
}