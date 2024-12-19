package com.hutech.demo.controller;

import com.hutech.demo.createrequest.CreateReviewRequest;
import com.hutech.demo.response.ReviewResponse;
import com.hutech.demo.service.inf.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private IReviewService ReviewService;
    @PostMapping("/create")
    public ResponseEntity<ReviewResponse> createReview(@RequestBody CreateReviewRequest request) {
        ReviewResponse newReview = ReviewService.createReview(request);
        return ResponseEntity.ok(newReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
        ReviewResponse review = ReviewService.getReviewById(id);
        return review != null ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public List<ReviewResponse> getAllReviews() {
        return ReviewService.getAllReviews();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        ReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}