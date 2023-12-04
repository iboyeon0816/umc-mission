package umc.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apipayload.status.ErrorStatus;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final ErrorStatus status;
}
