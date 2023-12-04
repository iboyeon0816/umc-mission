package umc.spring.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodType;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.exception.ex.FoodTypeException;
import umc.spring.repository.FoodTypeRepository;
import umc.spring.repository.MemberRepository;
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

    @Override
    public Member joinMember(JoinDTO joinDTO) {

        Member member = MemberConverter.toMember(joinDTO);
        addFoodTypeToMember(joinDTO, member);

        return memberRepository.save(member);
    }

    private void addFoodTypeToMember(JoinDTO joinDTO, Member member) {
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
