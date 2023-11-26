package umc.spring.exception.ex;

import umc.spring.apiPayload.status.ErrorStatus;
import umc.spring.exception.GeneralException;

public class TempException extends GeneralException {
    public TempException(ErrorStatus status) {
        super(status);
    }
}
