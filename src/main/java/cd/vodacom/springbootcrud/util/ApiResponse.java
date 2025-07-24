package cd.vodacom.springbootcrud.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {
    private int statusCode;
    private String statusText;
    private String message;
    private T data;

    public ApiResponse(int statusCode, String statusText, String message, T data) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.message = message;
        this.data = data;
    }

}
