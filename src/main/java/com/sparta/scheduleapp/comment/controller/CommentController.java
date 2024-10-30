package com.sparta.scheduleapp.comment.controller;

import com.sparta.scheduleapp.comment.dto.request.AddCommentRequestDto;
import com.sparta.scheduleapp.comment.dto.request.EditCommentRequestDto;
import com.sparta.scheduleapp.common.dto.ResponseDto;
import com.sparta.scheduleapp.comment.service.CommentService;
import com.sparta.scheduleapp.common.exception.NotValidRequestException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/schedule/{scheduleId}/comments")
    public ResponseEntity<ResponseDto> addComment(@PathVariable Long scheduleId,
                                                  @RequestBody @Valid AddCommentRequestDto reqDto,
                                                  Errors errors,
                                                  @RequestAttribute("userId") Long jwtUserId
    ) {
        if (errors.hasErrors()) {
            String field = errors.getFieldError().getField();
            String message = errors.getFieldError().getDefaultMessage();
            throw new NotValidRequestException("ERR001", field + " 필드에 대한 에러 : " + message);
        }
        ResponseDto resDto = commentService.addComment(jwtUserId, scheduleId, reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }
    @GetMapping("/schedule/{scheduleId}/comments")
    public ResponseEntity<ResponseDto> retrieveComments(@PathVariable Long scheduleId) {

        ResponseDto resDto = commentService.retrieveComment(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @PutMapping("/schedule/{scheduleId}/comments/{commentId}")
    public ResponseEntity<ResponseDto> editComment(@PathVariable Long scheduleId,
                                                   @PathVariable Long commentId,
                                                   @RequestBody @Valid EditCommentRequestDto reqDto,
                                                   Errors errors,
                                                   @RequestAttribute("userId") Long jwtUserId
    ) {
        if (errors.hasErrors()) {
            String field = errors.getFieldError().getField();
            String message = errors.getFieldError().getDefaultMessage();
            throw new NotValidRequestException("ERR001", field + " 필드에 대한 에러 : " + message);
        }

        ResponseDto resDto = commentService.editComment(jwtUserId, scheduleId, commentId, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @DeleteMapping("/schedule/{scheduleId}/comments/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestAttribute("userId") Long jwtUserId) {

        ResponseDto resDto = commentService.deleteComment(jwtUserId, scheduleId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }
}