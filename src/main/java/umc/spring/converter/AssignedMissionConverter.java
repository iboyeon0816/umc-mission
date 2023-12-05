package umc.spring.converter;

import umc.spring.domain.mapping.AssignedMission;
import umc.spring.web.dto.MemberResponseDTO.AssignedMissionDTO;

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
}
