package umc.spring.converter;


import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO.ReviewSaveDTO;
import umc.spring.web.dto.ReviewResponseDTO.SaveStoreResultDTO;

public class ReviewConverter {

    public static Review toReview(ReviewSaveDTO reviewSaveDTO) {
        return Review.builder()
                .score(reviewSaveDTO.getScore())
                .content(reviewSaveDTO.getContent())
                .build();
    }

    public static SaveStoreResultDTO toReviewRegResultDTO(Review review) {
        return SaveStoreResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
