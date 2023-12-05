package umc.spring.web.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewAddDTO {
        @NotNull
        private Long memberId; // 임시

        @NotNull
        @Max(5) @Min(0)
        private Float score;

        private String content;
    }
}
