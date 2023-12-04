package umc.spring.exception.ex;

import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.exception.GeneralException;

public class MemberException extends GeneralException {

    public MemberException(ErrorStatus status) {
        super(status);
    }
}
