package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.spring.apipayload.ApiResponse;
import umc.spring.apipayload.status.SuccessStatus;
import umc.spring.converter.AssignedMissionConverter;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.AssignedMission;
import umc.spring.service.member.MemberCommandService;
import umc.spring.web.dto.MemberRequestDTO.JoinDTO;
import umc.spring.web.dto.MemberResponseDTO.JoinResultDTO;
import umc.spring.web.dto.MissionRequestDTO.ChallengeDTO;

import javax.validation.Valid;

import static umc.spring.web.dto.MemberResponseDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<JoinResultDTO> join(@RequestBody @Valid JoinDTO joinDTO) {
        Member member = memberCommandService.joinMember(joinDTO);
        return ApiResponse.of(SuccessStatus._CREATED, MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/{memberId}/missions") // memberId 수정 필요
    public ApiResponse<AssignedMissionDTO> challenge(
            @PathVariable Long memberId, @RequestBody @Valid ChallengeDTO challengeDTO) {

        AssignedMission assignedMission = memberCommandService.addMission(memberId, challengeDTO);
        return ApiResponse.onSuccess(AssignedMissionConverter.toAssignedMissionDTO(assignedMission));
    }
}
