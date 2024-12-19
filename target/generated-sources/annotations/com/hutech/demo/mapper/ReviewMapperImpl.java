package com.hutech.demo.mapper;

import com.hutech.demo.createrequest.CreateReviewRequest;
import com.hutech.demo.model.Review;
import com.hutech.demo.response.ReviewResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review toReview(CreateReviewRequest request) {
        if ( request == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.rating( request.getRating() );
        review.comment( request.getComment() );

        return review.build();
    }

    @Override
    public ReviewResponse toResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setId( review.getId() );
        reviewResponse.setBooking( review.getBooking() );
        reviewResponse.setRating( review.getRating() );
        reviewResponse.setComment( review.getComment() );
        reviewResponse.setCreatedAt( review.getCreatedAt() );
        reviewResponse.setUpdatedAt( review.getUpdatedAt() );

        return reviewResponse;
    }

    @Override
    public List<ReviewResponse> getList(List<Review> reviews) {
        if ( reviews == null ) {
            return null;
        }

        List<ReviewResponse> list = new ArrayList<ReviewResponse>( reviews.size() );
        for ( Review review : reviews ) {
            list.add( toResponse( review ) );
        }

        return list;
    }
}
