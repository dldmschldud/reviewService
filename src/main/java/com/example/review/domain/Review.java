package com.example.review.domain;

import com.example.review.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Review", indexes = { @Index(name = "idx_product_id", columnList = "product_id") })
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private int score;

    @Column
    private String content;

    @Column
    private String imageUrl;

    public Review(Product product,ReviewRequestDto reviewRequestDto){
        this.product = product;
        this.userId = reviewRequestDto.getUserId();
        this.score = reviewRequestDto.getScore();
        this.content = reviewRequestDto.getContent();
    }
}
