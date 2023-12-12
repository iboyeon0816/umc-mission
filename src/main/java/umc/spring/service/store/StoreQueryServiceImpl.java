package umc.spring.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    private static final int DEFAULT_SIZE = 10;

    @Override
    public boolean exists(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Page<Review> getReviews(Long storeId, Integer page) {
        Store store = storeRepository.getReferenceById(storeId);
        return reviewRepository.findAllByStore(store, PageRequest.of(page, DEFAULT_SIZE));
    }

    @Override
    public Page<Mission> getMissions(Long storeId, Integer page) {
        Store store = storeRepository.getReferenceById(storeId);
        return missionRepository.findAllByStore(store, PageRequest.of(page, DEFAULT_SIZE));
    }

}
