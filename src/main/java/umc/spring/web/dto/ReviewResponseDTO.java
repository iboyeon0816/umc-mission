package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewAddResultDTO {
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDetailListDTO {
        private List<ReviewDetailDTO> reviewDetailDTOS;
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
    public static class ReviewDetailDTO {
        private String ownerNickname;
        private Float score;
        private String content;
        private LocalDate createdAt;
    }

}
