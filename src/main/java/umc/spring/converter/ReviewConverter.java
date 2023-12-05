package umc.spring.converter;


import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO.ReviewAddDTO;
import umc.spring.web.dto.ReviewResponseDTO.ReviewAddResultDTO;

public class ReviewConverter {

    public static Review toReview(ReviewAddDTO reviewAddDTO) {
        return Review.builder()
                .score(reviewAddDTO.getScore())
                .content(reviewAddDTO.getContent())
                .build();
    }

    public static ReviewAddResultDTO toReviewAddResultDTO(Review review) {
        return ReviewAddResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
