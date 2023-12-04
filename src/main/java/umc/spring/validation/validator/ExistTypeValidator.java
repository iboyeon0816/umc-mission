package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.service.foodtype.FoodTypeQueryService;
import umc.spring.validation.annotation.ExistType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ExistTypeValidator implements ConstraintValidator<ExistType, Long> {

    private final FoodTypeQueryService foodTypeQueryService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        boolean isValid = foodTypeQueryService.exist(id);

        if (!isValid) {
            context.disableDefaultConstraintViolation(); // 기본 에러 메시지 제거
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_TYPE_NOT_FOUND.getMessage())
                    .addConstraintViolation(); // 에러 메시지 지정
        }
        return isValid;
    }
}
