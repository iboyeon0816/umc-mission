package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO.MissionAddDTO;
import umc.spring.web.dto.MissionResponseDTO.MissionAddResultDTO;

public class MissionConverter {

    public static Mission toMission(MissionAddDTO missionAddDTO) {
        return Mission.builder()
                .price(missionAddDTO.getPrice())
                .point(missionAddDTO.getPoint())
                .pointType(missionAddDTO.getPointType())
                .deadline(missionAddDTO.getDeadline())
                .build();
    }

    public static MissionAddResultDTO toMissionAddResultDTO(Mission mission) {
        return MissionAddResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

}
