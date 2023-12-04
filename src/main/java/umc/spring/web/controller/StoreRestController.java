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
import umc.spring.service.mission.MissionCommandService;
import umc.spring.service.review.ReviewCommandService;
import umc.spring.service.store.StoreCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequestDTO.AddMissionDTO;
import umc.spring.web.dto.ReviewResponseDTO.AddReviewResultDTO;
import umc.spring.web.dto.StoreResponseDTO.SaveStoreResultDTO;

import javax.validation.Valid;

import static umc.spring.web.dto.MissionResponseDTO.*;
import static umc.spring.web.dto.ReviewRequestDTO.*;
import static umc.spring.web.dto.StoreRequestDTO.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SaveStoreResultDTO> saveStore(@RequestBody @Valid SaveStoreDTO saveStoreDTO) {
        Store store = storeCommandService.saveStore(saveStoreDTO);
        return ApiResponse.of(SuccessStatus._CREATED, StoreConverter.toSaveStoreResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AddReviewResultDTO> addReview(@PathVariable @ExistStore Long storeId,
                                                     @RequestBody @Valid AddReviewDTO addReviewDTO) {

        Review review = reviewCommandService.addReview(storeId, addReviewDTO);
        return ApiResponse.of(SuccessStatus._CREATED, ReviewConverter.toAddReviewResultDTO(review));
    }

    @PostMapping("/{storeId}/missions")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AddMissionResultDTO> addMission(@PathVariable @ExistStore Long storeId,
                                                       @RequestBody @Valid AddMissionDTO addMissionDTO) {
        Mission mission = missionCommandService.addMission(storeId, addMissionDTO);
        return ApiResponse.of(SuccessStatus._CREATED, MissionConverter.toAddMissionResultDTO(mission));
    }
}
