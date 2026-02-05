package com.example.lets_play.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.example.lets_play.model.dto.HttpErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleProductNotFound(
            ProductNotFoundException ex, WebRequest request) {
        
        HttpErrorResponse error = HttpErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpErrorResponse> handleAccessDenied(
            AccessDeniedException ex, WebRequest request) {
        
        HttpErrorResponse error = HttpErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error("Access Denied")
                .message("You don't have permission to access this resource")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpErrorResponse> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        
        HttpErrorResponse error = HttpErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error("Unauthorized")
                .message("Authentication failed")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<HttpErrorResponse> handleEmailAlreadyExistException(
            EmailAlreadyExistException ex, WebRequest request) {
        
        HttpErrorResponse error = HttpErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleEmailNotFoundException(
            EmailNotFoundException ex, WebRequest request) {
        HttpErrorResponse error = HttpErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message("Email Not found")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<HttpErrorResponse> handleUsernameAlreadyExistsException(
            UsernameAlreadyExistsException ex, WebRequest request) {
        HttpErrorResponse error = HttpErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message("Username Not found")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<HttpErrorResponse> handleGenericException(
//             Exception ex, WebRequest request) {
        
//         System.out.println(String.format("Unexpected error occurred: %s", ex));
//         HttpErrorResponse error = HttpErrorResponse.builder()
//                 .timestamp(LocalDateTime.now())
//                 .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                 .error("Internal Server Error")
//                 .message("An unexpected error occurred. Please try again later.")
//                 .path(request.getDescription(false).replace("uri=", ""))
//                 .build();
        
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//     }
}
