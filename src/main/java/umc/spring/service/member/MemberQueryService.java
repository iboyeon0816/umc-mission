package umc.spring.service.member;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.AssignedMission;

public interface MemberQueryService {

    Page<Review> getReviews(Long memberId, Integer page);

    Page<AssignedMission> getMissions(Long memberId, MissionStatus status, Integer page);
}
