package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apipayload.ApiResponse;
import umc.spring.apipayload.status.SuccessStatus;
import umc.spring.argument.PageCheck;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.store.StoreCommandService;
import umc.spring.service.store.StoreQueryService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.ReviewResponseDTO.ReviewAddResultDTO;
import umc.spring.web.dto.ReviewResponseDTO.ReviewDetailListDTO;
import umc.spring.web.dto.StoreResponseDTO.RegResultDTO;

import javax.validation.Valid;

import static umc.spring.web.dto.MissionRequestDTO.*;
import static umc.spring.web.dto.MissionResponseDTO.*;
import static umc.spring.web.dto.ReviewRequestDTO.*;
import static umc.spring.web.dto.StoreRequestDTO.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RegResultDTO> register(@RequestBody @Valid RegDTO regDTO) {
        Store store = storeCommandService.register(regDTO);
        return ApiResponse.of(SuccessStatus._CREATED, StoreConverter.toRegResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewAddResultDTO> addReview(@PathVariable @ExistStore Long storeId,
                                                     @RequestBody @Valid ReviewAddDTO reviewAddDTO) {

        Review review = storeCommandService.addReview(storeId, reviewAddDTO);
        return ApiResponse.of(SuccessStatus._CREATED, ReviewConverter.toReviewAddResultDTO(review));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(
            summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. " +
                    "query String 으로 page 번호를 주세요."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디"),
            @Parameter(name = "page", description = "페이지 번호")
    })
    public ApiResponse<ReviewDetailListDTO> getReviews(@PathVariable @ExistStore Long storeId,
                                                       @PageCheck Integer page) {
        Page<Review> reviewPage = storeQueryService.getReviews(storeId, page);
        return ApiResponse.onSuccess(ReviewConverter.toReviewDetailListDTO(reviewPage));
    }

    @PostMapping("/{storeId}/missions")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MissionAddResultDTO> addMission(@PathVariable @ExistStore Long storeId,
                                                       @RequestBody @Valid MissionAddDTO missionAddDTO) {
        Mission mission = storeCommandService.addMission(storeId, missionAddDTO);
        return ApiResponse.of(SuccessStatus._CREATED, MissionConverter.toMissionAddResultDTO(mission));
    }
}
