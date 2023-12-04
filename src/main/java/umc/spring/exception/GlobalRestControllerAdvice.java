package umc.spring.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umc.spring.apipayload.ApiResponse;
import umc.spring.apipayload.status.ErrorStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, headers, request, ErrorStatus._BAD_REQUEST, null);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = extractErrorInfo(ex);

        return handleExceptionInternal(ex, headers, request, ErrorStatus._BAD_REQUEST, errors);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {

        String errorCode = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));

        return handleExceptionInternal(ex, HttpHeaders.EMPTY, request, ErrorStatus.valueOf(errorCode), null);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleEx(Exception ex, WebRequest request) {

        pageNotFoundLogger.warn(ex.getMessage());

        return handleExceptionInternal(ex, HttpHeaders.EMPTY, request, ErrorStatus._INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleGeneralException(GeneralException ex, WebRequest request) {

        ErrorStatus errorStatus = ex.getStatus();

        return handleExceptionInternal(ex, HttpHeaders.EMPTY, request, errorStatus, null);
    }

    private static Map<String, String> extractErrorInfo(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    String fieldName = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                    errors.merge(fieldName, errorMessage,
                            (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
                });

        return errors;
    }

    private <T> ResponseEntity<Object> handleExceptionInternal(
            Exception ex, HttpHeaders headers, WebRequest request, ErrorStatus errorStatus, T data) {

        ApiResponse<T> body = ApiResponse.onFailure(errorStatus, data);

        return super.handleExceptionInternal(
                ex,
                body,
                headers,
                errorStatus.getHttpStatus(),
                request
        );
    }

}
