package cd.vodacom.springbootcrud.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
        return ResponseEntity.ok(new ApiResponse<>(200, "success", message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(201, "success", message, data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "error", message, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> conflict(String message) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(409, "error", message, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, "error", message, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(401, "error", message, null));
    }

    public static <T> ResponseEntity<ApiResponse<T>> internalError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, "error", message, null));
    }
}