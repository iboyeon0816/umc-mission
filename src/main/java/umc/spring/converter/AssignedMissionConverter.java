package umc.spring.converter;

import umc.spring.domain.mapping.AssignedMission;
import umc.spring.web.dto.MemberResponseDTO.AssignedMissionResultDTO;

public class AssignedMissionConverter {
    public static AssignedMission toAssignedMission() {
        return AssignedMission.builder().build();
    }

    public static AssignedMissionResultDTO toAssignedMissionResultDTO(AssignedMission assignedMission) {
        return AssignedMissionResultDTO.builder()
                .assignedMissionId(assignedMission.getId())
                .createdAt(assignedMission.getCreatedAt())
                .build();
    }
}
