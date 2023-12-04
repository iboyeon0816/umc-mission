package umc.spring.service.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.exception.ex.MemberException;
import umc.spring.exception.ex.StoreException;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import javax.transaction.Transactional;

import static umc.spring.web.dto.ReviewRequestDTO.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Review addReview(Long storeId, AddReviewDTO addReviewDTO) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(addReviewDTO.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toReview(addReviewDTO);

        review.setStore(store);
        review.setMember(member);

        return reviewRepository.save(review);
    }
}
