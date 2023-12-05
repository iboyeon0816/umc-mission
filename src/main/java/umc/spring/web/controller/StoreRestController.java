package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apipayload.ApiResponse;
import umc.spring.apipayload.status.SuccessStatus;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.store.StoreCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.ReviewResponseDTO.ReviewAddResultDTO;
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

    @PostMapping("/{storeId}/missions")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MissionAddResultDTO> addMission(@PathVariable @ExistStore Long storeId,
                                                       @RequestBody @Valid MissionAddDTO missionAddDTO) {
        Mission mission = storeCommandService.addMission(storeId, missionAddDTO);
        return ApiResponse.of(SuccessStatus._CREATED, MissionConverter.toMissionAddResultDTO(mission));
    }
}
