package umc.spring.service.member;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;

public interface MemberQueryService {

    Page<Review> getReviews(Long memberId, Integer page);
}
