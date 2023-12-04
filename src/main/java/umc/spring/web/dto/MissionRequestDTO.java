package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.enums.PointType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDTO {
        @NotNull
        private Integer price;
        @NotNull
        private Integer point;
        @NotNull
        private PointType pointType;
        @NotNull
        private LocalDate deadline;
    }

}
