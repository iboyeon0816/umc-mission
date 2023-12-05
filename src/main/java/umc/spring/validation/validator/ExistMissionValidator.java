package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.service.mission.MissionQueryService;
import umc.spring.validation.annotation.ExistMission;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ExistMissionValidator implements ConstraintValidator<ExistMission, Long> {

    private final MissionQueryService missionQueryService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        boolean isValid = missionQueryService.exist(id);

        if (!isValid) {
            context.disableDefaultConstraintViolation(); // 기본 에러 메시지 제거
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.getMessage())
                    .addConstraintViolation(); // 에러 메시지 지정
        }
        return isValid;
    }
}
