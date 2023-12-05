package umc.spring.service.store;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO.MissionAddDTO;
import umc.spring.web.dto.ReviewRequestDTO.ReviewAddDTO;

import static umc.spring.web.dto.StoreRequestDTO.*;

public interface StoreCommandService {

    Store register(RegDTO regDTO);

    Review addReview(Long storeId, ReviewAddDTO reviewAddDTO);

    Mission addMission(Long storeId, MissionAddDTO missionAddDTO);
}
