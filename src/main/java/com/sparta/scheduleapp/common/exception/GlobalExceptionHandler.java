package com.sparta.scheduleapp.common.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sparta.scheduleapp.common.dto.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // 토큰을 발견하지 못해서 발생한 예외처리 (Filter에서 발생한 예외를 이곳에서 잡아줄 수 없다..)
    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ResponseDto> handleTokenNotFound(TokenNotFoundException ex) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto); // 400
    }

    // 엔티티를 찾지 못하는 예외처리
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto> handleEntityNotFound(EntityNotFoundException ex) {
        String errorMessage = ex.getMessage();
        ResponseDto responseDto = new ResponseDto(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto); // 404
    }

    // 유효성 검사에서 발생한 모든 오류를 예외처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 첫 번째 오류를 가져옵니다.
        FieldError firstError = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
        String errorMessage;

        if (firstError != null) {
            // 필드 이름과 기본 메시지를 사용하여 오류 메시지를 생성합니다.
            errorMessage = String.format("필드 '%s'에 대한 유효성 검사 실패: %s",
                    firstError.getField(),
                    firstError.getDefaultMessage());
        } else {
            errorMessage = "유효성 검사 실패";
        }

        ResponseDto responseDto = new ResponseDto(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto); // 400
    }

    // 잘못된 입력요청 때문에 발생한 예외처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMessage();
        if (ex.getCause() instanceof InvalidFormatException) {
            errorMessage = "잘못된 입력값입니다.";
        }
        ResponseDto responseDto = new ResponseDto(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto); // 400
    }

    // 사용자 권한이 없는 작업을 시도할 때 발생하는 예외처리
    @ExceptionHandler(UserAccessDeniedException.class)
    public ResponseEntity<ResponseDto> handleAccessDenied(UserAccessDeniedException ex) {
        String errorMessage = ex.getMessage();
        ResponseDto responseDto = new ResponseDto(errorMessage);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDto); // 403
    }

    // 잘못된 인자를 전달했을 때 발생하는 예외처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto> handleIllegalArgument(IllegalArgumentException ex) {
        String errorMessage = ex.getMessage();
        ResponseDto responseDto = new ResponseDto(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto); // 400
    }
}
