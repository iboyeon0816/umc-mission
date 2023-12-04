package umc.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.exception.ex.StoreException;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO.AddMissionDTO;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    @Override
    public Mission addMission(Long storeId, AddMissionDTO addMissionDTO) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toMission(addMissionDTO);

        mission.setStore(store);

        return missionRepository.save(mission);
    }
}
