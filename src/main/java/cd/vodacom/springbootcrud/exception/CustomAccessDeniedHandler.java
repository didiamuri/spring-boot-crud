package cd.vodacom.springbootcrud.exception;

import cd.vodacom.springbootcrud.util.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ApiResponse<String> apiResponse = new ApiResponse<>(
                HttpServletResponse.SC_FORBIDDEN,
                "FORBIDDEN",
                accessDeniedException.getMessage(),
                null
        );

        new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
    }
}