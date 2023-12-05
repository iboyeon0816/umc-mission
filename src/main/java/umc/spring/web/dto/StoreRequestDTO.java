package umc.spring.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StoreRequestDTO {

    @Getter
    public static class RegDTO {

        @NotNull
        private Long foodTypeId;

        @NotBlank
        @Size(max = 20)
        private String name;

        @NotNull
        @Size(max = 100)
        private String address;
    }
}
