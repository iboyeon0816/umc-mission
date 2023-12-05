package umc.spring.service.member;

import umc.spring.domain.Member;
import umc.spring.domain.mapping.AssignedMission;
import umc.spring.web.dto.MemberRequestDTO.JoinDTO;
import umc.spring.web.dto.MissionRequestDTO.ChallengeDTO;

public interface MemberCommandService {

    Member joinMember(JoinDTO joinDTO);

    AssignedMission addMission(Long memberId, ChallengeDTO challengeDTO);
}
