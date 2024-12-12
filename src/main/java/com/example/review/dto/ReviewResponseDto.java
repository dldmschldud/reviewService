package com.example.review.dto;

import com.example.review.domain.Review;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//상품에 대한 리뷰객체
@Data
public class ReviewResponseDto {

    private int totalCount;
    private float score;
    private int cursor;
    private List<ReviewDto> reviews;

    public ReviewResponseDto(int totalCount, float score, int cursor, List<ReviewDto> reviews){
        this.totalCount = totalCount;
        this.score = score;
        this.cursor = cursor;
        this.reviews = reviews;

    }


}
