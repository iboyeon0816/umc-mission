package umc.spring.validation.annotation;

import umc.spring.validation.validator.ExistStoreValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistStoreValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {

    String message() default "해당하는 가게가 존재하지 않습니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
