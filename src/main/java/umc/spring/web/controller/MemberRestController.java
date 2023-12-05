package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apipayload.ApiResponse;
import umc.spring.apipayload.status.SuccessStatus;
import umc.spring.converter.AssignedMissionConverter;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.AssignedMission;
import umc.spring.service.member.MemberCommandService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.MemberRequestDTO.JoinDTO;
import umc.spring.web.dto.MemberResponseDTO.JoinResultDTO;

import javax.validation.Valid;

import static umc.spring.web.dto.MemberResponseDTO.*;

@Validated
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

    @PostMapping("/{memberId}/missions/{missionId}") // memberId 수정 필요
    public ApiResponse<AssignedMissionResultDTO> addMission(
            @PathVariable Long memberId,
            @PathVariable @ExistMission Long missionId) {

        AssignedMission assignedMission = memberCommandService.addMission(memberId, missionId);
        return ApiResponse.onSuccess(AssignedMissionConverter.toAssignedMissionResultDTO(assignedMission));
    }
}
