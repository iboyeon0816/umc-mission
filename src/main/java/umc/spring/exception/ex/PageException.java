package umc.spring.exception.ex;

import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.exception.GeneralException;

public class PageException extends GeneralException {
    public PageException(ErrorStatus status) {
        super(status);
    }
}
