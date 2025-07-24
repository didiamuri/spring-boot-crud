package cd.vodacom.springbootcrud.exception;

import cd.vodacom.springbootcrud.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            errors.put(err.getField(), err.getDefaultMessage());
        }

        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(), "error", HttpStatus.BAD_REQUEST.getReasonPhrase(), errors
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<String>> handleGenericError(Exception ex) {
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}