package umc.spring.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.converter.AssignedMissionConverter;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodType;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.AssignedMission;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.exception.ex.MemberException;
import umc.spring.exception.ex.MissionException;
import umc.spring.repository.AssignedMissionRepository;
import umc.spring.repository.FoodTypeRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MemberRequestDTO.JoinDTO;
import umc.spring.web.dto.MissionRequestDTO.ChallengeDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final MissionRepository missionRepository;
    private final AssignedMissionRepository assignedMissionRepository;

    @Override
    public Member joinMember(JoinDTO joinDTO) {

        Member member = MemberConverter.toMember(joinDTO);
        setFoodTypes(joinDTO.getPreferredFoodTypes(), member);

        return memberRepository.save(member);
    }

    @Override
    public AssignedMission addMission(Long memberId, ChallengeDTO challengeDTO) {

        AssignedMission am = AssignedMissionConverter.toAssignedMission();
        setMemberAndMissionToAM(memberId, challengeDTO.getMissionId(), am);

        return assignedMissionRepository.save(am);
    }

    private void setFoodTypes(List<Long> foodTypeIds, Member member) {
        List<FoodType> foodTypes = foodTypeIds.stream()
                .map(foodTypeRepository::getReferenceById)
                .collect(Collectors.toList());

        List<MemberPrefer> memberPrefers = MemberPreferConverter.toMemberPrefers(foodTypes);
        memberPrefers.forEach(memberPrefer -> memberPrefer.setMember(member));
    }

    private void setMemberAndMissionToAM(Long memberId, Long missionId, AssignedMission am) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(ErrorStatus.MISSION_NOT_FOUND));

        validateNewMission(member, mission);

        am.setMission(mission);
        am.setMember(member);
    }

    private void validateNewMission(Member member, Mission mission) {
        boolean exists = assignedMissionRepository.existsByMemberAndMission(member, mission);
        if (exists) {
            throw new MemberException(ErrorStatus.ALREADY_ASSIGNED_MISSION);
        }
    }

}
