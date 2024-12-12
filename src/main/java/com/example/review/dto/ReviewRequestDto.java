package com.example.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
//사용자가 입력한 리뷰정보 받아오는 객체
public class ReviewRequestDto {
    private Long userId;
    @Min(value = 1, message = "1-5점 사이")
    @Max(value = 5, message = "1-5점 사이")
    private int score;
    private String content;
}
