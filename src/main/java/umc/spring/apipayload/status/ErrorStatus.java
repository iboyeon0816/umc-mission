package umc.spring.apipayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    // COMMON
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다"),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다"),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다"),

    // FOOD_TYPE
    FOOD_TYPE_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOODTYPE4001", "해당하는 음식 종류가 존재하지 않습니다"),

    // STORE
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001", "해당하는 가게가 존재하지 않습니다"),

    // MEMBER
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "해당하는 멤버가 존재하지 않습니다"),
    ALREADY_ASSIGNED_MISSION(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 할당된 미션입니다"),

    // MISSION
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "해당하는 미션이 존재하지 않습니다"),

    // PAGE
    PAGE_REQUIRED(HttpStatus.BAD_REQUEST, "PAGE4001", "페이지는 필수 입력값입니다."),
    PAGE_NOT_POSITIVE(HttpStatus.BAD_REQUEST, "PAGE4002", "페이지 값은 양수이어야 합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
