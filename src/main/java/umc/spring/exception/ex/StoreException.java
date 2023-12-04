package umc.spring.exception.ex;

import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(ErrorStatus status) {
        super(status);
    }
}
