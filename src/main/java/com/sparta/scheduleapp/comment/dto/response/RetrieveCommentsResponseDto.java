package com.sparta.scheduleapp.comment.dto.response;

import com.sparta.scheduleapp.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class RetrieveCommentsResponseDto extends ResponseDto {

    private List<CommentDto> comments;

    public RetrieveCommentsResponseDto(String message, List<Comment> comments) {
        super(message);
        this.comments = comments.stream()
                .map(CommentDto::new)
                .toList();
    }
}
