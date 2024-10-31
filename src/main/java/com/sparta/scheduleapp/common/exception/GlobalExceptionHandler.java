package com.sparta.scheduleapp.common.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sparta.scheduleapp.common.dto.ErrorResponseDto;
import com.sparta.scheduleapp.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // 토큰을 발견하지 못해서 발생한 예외처리 (Filter에서 발생한 예외를 이곳에서 잡아줄 수 없다..)
    /*@ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ResponseDto> handleTokenNotFound(TokenNotFoundException ex) {
        ResponseDto responseDto = new ErrorResponseDto("ERR000", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto); // 400
    }*/

    // 엔티티를 찾지 못하는 예외처리
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> handleEntityNotFound(ResourceNotFoundException ex) {
        String errorMessage = ex.getMessage();
        String errorCode = ex.getErrorCode();
        ResponseDto responseDto = new ErrorResponseDto(errorCode, errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto); // 404
    }

    // 유효성 검사에서 발생한 모든 오류를 예외처리
    @ExceptionHandler(NotValidRequestException.class)
    public ResponseEntity<ResponseDto> handleValidationExceptions(NotValidRequestException ex) {
        String errorMessage = ex.getMessage();
        String errorCode = ex.getErrorCode();
        ResponseDto responseDto = new ErrorResponseDto(errorCode, errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto); // 400
    }

    // 사용자의 잘못된 요청 예외처리
    @ExceptionHandler(ClientBadRequestException.class)
    public ResponseEntity<ResponseDto> handleClientBadRequest(ClientBadRequestException ex) {
        String errorMessage = ex.getMessage();
        String errorCode = ex.getErrorCode();
        ResponseDto responseDto = new ErrorResponseDto(errorCode, errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto); // 400
    }

    // 잘못된 입력요청 때문에 발생한 예외처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMessage();
        String errorCode = "ERR004";
        if (ex.getCause() instanceof InvalidFormatException) {
            errorMessage = "잘못된 입력값입니다.";

        }
        ResponseDto responseDto = new ErrorResponseDto(errorCode, errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto); // 400
    }

    // 사용자 권한이 없는 작업을 시도할 때 발생하는 예외처리
    @ExceptionHandler(UserAccessDeniedException.class)
    public ResponseEntity<ResponseDto> handleAccessDenied(UserAccessDeniedException ex) {
        String errorMessage = ex.getMessage();
        String errorCode = ex.getErrorCode();
        ResponseDto responseDto = new ErrorResponseDto(errorCode, errorMessage);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDto); // 403
    }

    // 로그인 인증 시도할 때 발생하는 예외처리
    @ExceptionHandler(LoginAuthorizationException.class)
    public ResponseEntity<ResponseDto> handleLoginAuthorization(LoginAuthorizationException ex) {
        String errorMessage = ex.getMessage();
        String errorCode = ex.getErrorCode();
        ResponseDto responseDto = new ErrorResponseDto(errorCode, errorMessage);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto); // 401
    }

}
