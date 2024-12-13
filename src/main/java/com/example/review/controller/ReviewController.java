package com.example.review.controller;

import com.example.review.dto.ReviewRequestDto;
import com.example.review.dto.ReviewResponseDto;
import com.example.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Log4j2
@RequestMapping("/products")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{productId}/reviews")
    public ResponseEntity<ReviewResponseDto> getReviews(@PathVariable("productId") Long id,
                                                        @RequestParam(value ="cursor", required = false) Long cursor,
                                                        @RequestParam(value ="size",defaultValue = "10") int size){
        try{
            ReviewResponseDto reviewResponseDto = reviewService.getReviews(id,cursor,size);
            return new ResponseEntity<>(reviewResponseDto,HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{productId}/reviews")
    public ResponseEntity<String> createReview(@PathVariable("productId") Long id, @RequestBody ReviewRequestDto reviewRequestDto) {
        try {
            reviewService.createReview(id, reviewRequestDto);
            return new ResponseEntity<>("리뷰 저장 성공", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("해당 상품없음", HttpStatus.NOT_FOUND);
        } catch(IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
