package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.AssignedMission;
import umc.spring.web.dto.MemberResponseDTO.AssignedMissionDTO;
import umc.spring.web.dto.MissionResponseDTO.AMDetailDTO;
import umc.spring.web.dto.MissionResponseDTO.AMDetailListDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AssignedMissionConverter {
    public static AssignedMission toAssignedMission() {
        return AssignedMission.builder().build();
    }

    public static AssignedMissionDTO toAssignedMissionDTO(AssignedMission assignedMission) {
        return AssignedMissionDTO.builder()
                .assignedMissionId(assignedMission.getId())
                .createdAt(assignedMission.getCreatedAt())
                .build();
    }

    public static AMDetailListDTO toAMDetailListDTO(Page<AssignedMission> missionPage) {
        List<AMDetailDTO> amDetailDTOS = missionPage.getContent().stream()
                .map(AssignedMissionConverter::toAMDetailDTO)
                .collect(Collectors.toList());

        return AMDetailListDTO.builder()
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .totalPages(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(amDetailDTOS.size())
                .AMDetailDTOs(amDetailDTOS)
                .build();
    }

    public static AMDetailDTO toAMDetailDTO(AssignedMission am) {
        return AMDetailDTO.builder()
                .success(am.getSuccess())
                .price(am.getMission().getPrice())
                .point(am.getMission().getPoint())
                .pointType(am.getMission().getPointType())
                .storeName(am.getMission().getStore().getName())
                .build();
    }
}
