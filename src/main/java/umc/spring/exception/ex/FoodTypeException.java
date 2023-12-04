package umc.spring.exception.ex;

import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.exception.GeneralException;

public class FoodTypeException extends GeneralException {
    public FoodTypeException(ErrorStatus status) {
        super(status);
    }
}
