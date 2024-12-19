package com.hutech.demo.mapper;

import com.hutech.demo.createrequest.CreateReviewRequest;
import com.hutech.demo.model.Review;
import com.hutech.demo.response.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(CreateReviewRequest request);

    ReviewResponse toResponse(Review review);
    List<ReviewResponse> getList(List<Review> reviews);
}
