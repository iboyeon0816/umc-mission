package umc.spring.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.exception.ex.FoodTypeException;
import umc.spring.exception.ex.MemberException;
import umc.spring.exception.ex.StoreException;
import umc.spring.repository.*;
import umc.spring.web.dto.MissionRequestDTO.MissionAddDTO;
import umc.spring.web.dto.StoreRequestDTO.RegDTO;

import javax.transaction.Transactional;

import static umc.spring.web.dto.ReviewRequestDTO.*;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Store register(RegDTO regDTO) {

        Store store = StoreConverter.toStore(regDTO);
        setFoodTypeToStore(regDTO.getFoodTypeId(), store);

        return storeRepository.save(store);
    }

    @Override
    public Review addReview(Long storeId, ReviewAddDTO reviewAddDTO) {

        Review review = ReviewConverter.toReview(reviewAddDTO);
        setMemberAndStoreToReview(storeId, reviewAddDTO.getMemberId(), review);

        return reviewRepository.save(review);
    }

    @Override
    public Mission addMission(Long storeId, MissionAddDTO missionAddDTO) {

        Mission mission = MissionConverter.toMission(missionAddDTO);
        setStoreToMission(storeId, mission);

        return missionRepository.save(mission);
    }

    private void setFoodTypeToStore(Long foodTypeId, Store store) {
        FoodType foodType = foodTypeRepository.findById(foodTypeId)
                .orElseThrow(() -> new FoodTypeException(ErrorStatus.FOOD_TYPE_NOT_FOUND));

        store.setFoodType(foodType);
    }

    private void setMemberAndStoreToReview(Long storeId, Long memberId, Review review) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        review.setStore(store);
        review.setMember(member);
    }

    private void setStoreToMission(Long storeId, Mission mission) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));

        mission.setStore(store);
    }
}
