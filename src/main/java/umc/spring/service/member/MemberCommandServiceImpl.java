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
import umc.spring.exception.ex.FoodTypeException;
import umc.spring.exception.ex.MemberException;
import umc.spring.exception.ex.MissionException;
import umc.spring.repository.AssignedMissionRepository;
import umc.spring.repository.FoodTypeRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MemberRequestDTO.JoinDTO;

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
        addFoodType(joinDTO, member);

        return memberRepository.save(member);
    }

    @Override
    public AssignedMission addMission(Long memberId, Long missionId) {

        validateNewMission(memberId, missionId);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(ErrorStatus.MISSION_NOT_FOUND));

        AssignedMission assignedMission = AssignedMissionConverter.toAssignedMission();
        assignedMission.setMission(mission);
        assignedMission.setMember(member);

        return assignedMissionRepository.save(assignedMission);
    }

    private void validateNewMission(Long memberId, Long missionId) {
        boolean alreadyExists = assignedMissionRepository
                .existsByMemberIdAndMissionId(memberId, missionId);

        if (alreadyExists) {
            throw new MemberException(ErrorStatus.ALREADY_ASSIGNED_MISSION);
        }
    }

    private void addFoodType(JoinDTO joinDTO, Member member) {
        List<FoodType> foodTypes = findFoodTypes(joinDTO);

        List<MemberPrefer> memberPrefers = MemberPreferConverter.toMemberPrefers(foodTypes);
        memberPrefers.forEach(memberPrefer -> memberPrefer.setMember(member));
    }

    private List<FoodType> findFoodTypes(JoinDTO joinDTO) {
        return joinDTO.getPreferredFoodTypes().stream()
                .map(foodTypeId ->
                        foodTypeRepository.findById(foodTypeId)
                        .orElseThrow(() -> new FoodTypeException(ErrorStatus.FOOD_TYPE_NOT_FOUND)))
                .collect(Collectors.toList());
    }
}
