package umc.spring.service.review;

import umc.spring.domain.Review;

import static umc.spring.web.dto.ReviewRequestDTO.*;

public interface ReviewCommandService {

    Review addReview(Long storeId, AddReviewDTO addReviewDTO);
}
