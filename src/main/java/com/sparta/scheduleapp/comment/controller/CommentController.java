package com.sparta.scheduleapp.comment.controller;

import com.sparta.scheduleapp.comment.dto.request.AddCommentRequestDto;
import com.sparta.scheduleapp.comment.dto.request.EditCommentRequestDto;
import com.sparta.scheduleapp.comment.dto.response.ResponseDto;
import com.sparta.scheduleapp.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/schedule/{scheduleId}/comments")
    public ResponseEntity<ResponseDto> addComment(@PathVariable Long scheduleId, @RequestBody AddCommentRequestDto reqDto) {

        ResponseDto resDto = commentService.addComment(scheduleId, reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }
    @GetMapping("/schedule/{scheduleId}/comments")
    public ResponseEntity<ResponseDto> retrieveComments(@PathVariable Long scheduleId) {

        ResponseDto resDto = commentService.retrieveComments(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @PutMapping("/schedule/{scheduleId}/comments/{commentId}")
    public ResponseEntity<ResponseDto> editComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody EditCommentRequestDto reqDto) {

        ResponseDto resDto = commentService.editComments(scheduleId, commentId, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }
}