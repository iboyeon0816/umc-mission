package umc.spring.service.store;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;

public interface StoreQueryService {

    boolean exists(Long id);

    Page<Review> getReviews(Long storeId, Integer page);

    Page<Mission> getMissions(Long storeId, Integer page);
}
