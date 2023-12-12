package umc.spring.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public boolean exists(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Page<Review> getReviews(Long storeId, Integer page) {
        Store store = storeRepository.getReferenceById(storeId);
        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

}
