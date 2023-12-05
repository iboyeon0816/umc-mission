package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.enums.PointType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class MissionAddDTO {
        @NotNull
        private Integer price;
        @NotNull
        private Integer point;
        @NotNull
        private PointType pointType;
        @NotNull
        private LocalDate deadline;
    }

    @Getter
    public static class ChallengeDTO {
        @NotNull
        private Long missionId;
    }

}
