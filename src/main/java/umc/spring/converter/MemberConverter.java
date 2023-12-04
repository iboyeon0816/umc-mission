package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDTO.JoinDTO;
import umc.spring.web.dto.MemberResponseDTO.JoinResultDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static JoinResultDTO toJoinResultDTO(Member member) {
        return JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(JoinDTO joinDTO) {
        return Member.builder()
                .name(joinDTO.getName())
                .address(joinDTO.getAddress())
                .gender(joinDTO.getGender())
                .birthday(joinDTO.getBirthday())
                .memberPrefers(new ArrayList<>())
                .build();
    }
}
