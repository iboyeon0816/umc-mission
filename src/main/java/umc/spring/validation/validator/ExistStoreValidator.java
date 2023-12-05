package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.service.store.StoreQueryService;
import umc.spring.validation.annotation.ExistStore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ExistStoreValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreQueryService storeQueryService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        boolean isValid = storeQueryService.exists(id);

        if (!isValid) {
            context.disableDefaultConstraintViolation(); // 기본 에러 메시지 제거
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.getMessage())
                    .addConstraintViolation(); // 에러 메시지 지정
        }
        return isValid;
    }
}
