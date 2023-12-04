package umc.spring.service.member;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDTO.JoinDTO;

public interface MemberCommandService {

    Member joinMember(JoinDTO joinDTO);
}
