package umc.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPayload.status.ErrorStatus;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final ErrorStatus status;

}
