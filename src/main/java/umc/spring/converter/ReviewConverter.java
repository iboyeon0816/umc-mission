package umc.spring.converter;


import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO.ReviewAddDTO;
import umc.spring.web.dto.ReviewResponseDTO.ReviewAddResultDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static umc.spring.web.dto.ReviewResponseDTO.*;

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

    public static ReviewDetailListDTO toReviewDetailListDTO(Page<Review> reviewPage) {
        List<ReviewDetailDTO> reviewDetailDTOS = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewDetailDTO)
                .collect(Collectors.toList());

        return ReviewDetailListDTO.builder()
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .totalPages(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewDetailDTOS.size())
                .reviewDetailDTOS(reviewDetailDTOS)
                .build();
    }

    public static ReviewDetailDTO toReviewDetailDTO(Review review) {
        return ReviewDetailDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
