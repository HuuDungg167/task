package com.hutech.demo.service.imp;

import com.hutech.demo.createrequest.CreateReviewRequest;
import com.hutech.demo.mapper.ReviewMapper;
import com.hutech.demo.model.Booking;
import com.hutech.demo.model.Review;
import com.hutech.demo.repository.BookingRepository;
import com.hutech.demo.repository.ReviewRepository;
import com.hutech.demo.response.ReviewResponse;
import com.hutech.demo.service.inf.IReviewService;

import java.util.List;
@org.springframework.stereotype.Service
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewResponse createReview(CreateReviewRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId()).get();
        Review review = reviewMapper.toReview(request);
        review.setBooking(booking);
        reviewRepository.save(review);
        return reviewMapper.toResponse(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        Review review = reviewRepository.findById(id).get();
        if (review != null)
            return reviewMapper.toResponse(review);
        return null;
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        return reviewMapper.getList(reviewRepository.findAll());
    }
}
