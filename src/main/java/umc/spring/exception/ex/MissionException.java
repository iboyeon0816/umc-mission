package umc.spring.exception.ex;

import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.exception.GeneralException;

public class MissionException extends GeneralException {

    public MissionException(ErrorStatus status) {
        super(status);
    }
}
