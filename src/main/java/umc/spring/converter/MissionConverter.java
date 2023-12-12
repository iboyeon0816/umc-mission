package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO.MissionAddDTO;
import umc.spring.web.dto.MissionResponseDTO.MissionAddResultDTO;
import umc.spring.web.dto.MissionResponseDTO.MissionDetailDTO;
import umc.spring.web.dto.MissionResponseDTO.MissionDetailListDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionDetailListDTO toMissionDetailListDTO(Page<Mission> missionPage) {
        List<MissionDetailDTO> missionDetailDTOS = missionPage.getContent().stream()
                .map(MissionConverter::toMissionDetailDTO)
                .collect(Collectors.toList());

        return MissionDetailListDTO.builder()
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .totalPages(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionDetailDTOS.size())
                .missionDetailDTOS(missionDetailDTOS)
                .build();
    }

    public static MissionDetailDTO toMissionDetailDTO(Mission mission) {
        return MissionDetailDTO.builder()
                .price(mission.getPrice())
                .point(mission.getPoint())
                .pointType(mission.getPointType())
                .deadline(mission.getDeadline())
                .createdAt(LocalDate.from(mission.getCreatedAt()))
                .build();
    }
}
