package umc.spring.validation.annotation;

import umc.spring.validation.validator.ExistTypesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistTypesValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistTypes {

    String message() default "해당하는 음식 종류가 존재하지 않습니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
