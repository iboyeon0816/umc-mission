package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.PointType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionAddResultDTO {
        private Long missionId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailListDTO {
        private List<MissionDetailDTO> missionDetailDTOS;
        private Integer listSize;
        private Boolean isFirst;
        private Boolean isLast;
        private Integer totalPages;
        private Long totalElements;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailDTO {
        private Integer price;
        private Integer point;
        private PointType pointType;
        private LocalDate deadline;
        private LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AMDetailListDTO {
        private List<AMDetailDTO> AMDetailDTOs;
        private Integer listSize;
        private Boolean isFirst;
        private Boolean isLast;
        private Integer totalPages;
        private Long totalElements;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AMDetailDTO {
        private Boolean success;
        private Integer price;
        private Integer point;
        private PointType pointType;
        private LocalDate deadline;
        private LocalDate createdAt;
    }
}
