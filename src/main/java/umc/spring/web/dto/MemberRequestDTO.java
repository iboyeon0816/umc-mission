package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.enums.Gender;
import umc.spring.validation.annotation.ExistTypes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        @NotBlank
        @Size(max = 20)
        private String name;

        @NotNull
        @Size(max = 100)
        private String address;

        @NotNull
        private Gender gender;

        @NotNull
        private LocalDate birthday;

        @ExistTypes
        private List<Long> preferredFoodTypes;
    }
}
