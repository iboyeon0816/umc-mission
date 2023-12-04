package umc.spring.converter;


import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO.AddReviewDTO;
import umc.spring.web.dto.ReviewResponseDTO.AddReviewResultDTO;

public class ReviewConverter {

    public static Review toReview(AddReviewDTO addReviewDTO) {
        return Review.builder()
                .score(addReviewDTO.getScore())
                .content(addReviewDTO.getContent())
                .build();
    }

    public static AddReviewResultDTO toAddReviewResultDTO(Review review) {
        return AddReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
