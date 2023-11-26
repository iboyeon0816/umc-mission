package umc.spring.converter;

import static umc.spring.web.dto.TempResponseDTO.*;

public class TempConverter {
    public static TestDTO toTestDTO() {
        return TestDTO.builder()
                .testString("THIS IS TEST!")
                .build();
    }

    public static ExceptionDTO toExceptionDTO(Integer flag) {
        return ExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
