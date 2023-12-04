package umc.spring.service.mission;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO.AddMissionDTO;

public interface MissionCommandService {

    Mission addMission(Long storeId, AddMissionDTO addMissionDTO);
}
