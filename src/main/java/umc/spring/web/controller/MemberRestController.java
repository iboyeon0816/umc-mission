package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.spring.apipayload.ApiResponse;
import umc.spring.apipayload.status.SuccessStatus;
import umc.spring.argument.PageCheck;
import umc.spring.converter.AssignedMissionConverter;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.AssignedMission;
import umc.spring.service.member.MemberCommandService;
import umc.spring.service.member.MemberQueryService;
import umc.spring.web.dto.MemberRequestDTO.JoinDTO;
import umc.spring.web.dto.MemberResponseDTO.JoinResultDTO;
import umc.spring.web.dto.MissionRequestDTO.ChallengeDTO;
import umc.spring.web.dto.MissionResponseDTO.AMDetailListDTO;
import umc.spring.web.dto.ReviewResponseDTO.ReviewDetailListDTO;

import javax.validation.Valid;

import static umc.spring.web.dto.MemberResponseDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

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

    @GetMapping("/{memberId}/missions")
    @Operation(
            summary = "특정 멤버의 미션 목록 조회 API",
            description = "특정 멤버의 미션 목록을 조회하는 API이며, 조회할 미션의 상태를 지정해야 합니다. " +
                    "페이징이 포함되어 있습니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디"),
            @Parameter(name = "status", description = "미션의 진행 상태 (PROCEEDING, COMPLETED)"),
            @Parameter(name = "page", description = "페이지 번호")
    })
    public ApiResponse<AMDetailListDTO> getMissions(@PathVariable Long memberId,
                                                    @RequestParam MissionStatus status,
                                                    @PageCheck Integer page) {
        Page<AssignedMission> missionPage = memberQueryService.getMissions(memberId, status, page);
        return ApiResponse.onSuccess(AssignedMissionConverter.toAMDetailListDTO(missionPage));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(
            summary = "특정 멤버의 리뷰 목록 조회 API",
            description = "특정 멤버의 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. " +
                    "query String 으로 page 번호를 주세요."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디"),
            @Parameter(name = "page", description = "페이지 번호")
    })
    public ApiResponse<ReviewDetailListDTO> getReviews(@PathVariable Long memberId,
                                                       @PageCheck Integer page) {
        Page<Review> reviewPage = memberQueryService.getReviews(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.toReviewDetailListDTO(reviewPage));
    }
}
