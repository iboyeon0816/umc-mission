package umc.spring.service.store;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;

public interface StoreQueryService {

    boolean exists(Long id);

    Page<Review> getReviews(Long storeId, Integer page);
}
