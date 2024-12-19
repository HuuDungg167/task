package com.hutech.demo.service.inf;

import com.hutech.demo.createrequest.CreateReviewRequest;
import com.hutech.demo.response.ReviewResponse;

import java.util.List;

public interface IReviewService {
    ReviewResponse createReview(CreateReviewRequest request);
//    ReviewResponse updateReview(Long id, String status);
    void deleteReview(Long id);
    ReviewResponse getReviewById(Long id);
    List<ReviewResponse> getAllReviews();
}
