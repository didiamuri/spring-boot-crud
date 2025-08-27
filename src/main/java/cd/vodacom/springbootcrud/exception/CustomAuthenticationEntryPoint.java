package cd.vodacom.springbootcrud.exception;

import cd.vodacom.springbootcrud.util.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ApiResponse<String> apiResponse = new ApiResponse<>(
                HttpServletResponse.SC_UNAUTHORIZED,
                "UNAUTHORIZED",
                authException.getMessage(),
                null
        );

        new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
    }
}
