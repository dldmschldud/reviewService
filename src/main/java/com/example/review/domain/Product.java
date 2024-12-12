package com.example.review.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long reviewCount;

    @Column
    private Float score;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public void change(float userScore){
        float totalScore = this.score * this.reviewCount;
        totalScore += userScore;
        this.reviewCount += 1;
        this.score = totalScore / this.reviewCount;
    }
}
