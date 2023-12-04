package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO.AddMissionDTO;
import umc.spring.web.dto.MissionResponseDTO.AddMissionResultDTO;

public class MissionConverter {

    public static Mission toMission(AddMissionDTO addMissionDTO) {
        return Mission.builder()
                .price(addMissionDTO.getPrice())
                .point(addMissionDTO.getPoint())
                .pointType(addMissionDTO.getPointType())
                .deadline(addMissionDTO.getDeadline())
                .build();
    }

    public static AddMissionResultDTO toAddMissionResultDTO(Mission mission) {
        return AddMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

}
