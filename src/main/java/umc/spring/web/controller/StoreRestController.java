package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apipayload.ApiResponse;
import umc.spring.apipayload.status.SuccessStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.review.ReviewCommandService;
import umc.spring.service.store.StoreCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.ReviewRequestDTO.ReviewSaveDTO;
import umc.spring.web.dto.ReviewResponseDTO.SaveStoreResultDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

import static umc.spring.web.dto.StoreRequestDTO.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StoreResponseDTO.SaveStoreResultDTO> saveStore(@RequestBody @Valid StoreSaveDTO storeSaveDTO) {
        Store store = storeCommandService.saveStore(storeSaveDTO);
        return ApiResponse.of(SuccessStatus._CREATED, StoreConverter.toRegResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SaveStoreResultDTO> saveReview(@PathVariable @ExistStore Long storeId,
                                                      @RequestBody @Valid ReviewSaveDTO reviewSaveDTO) {

        Review review = reviewCommandService.saveReview(storeId, reviewSaveDTO);
        return ApiResponse.of(SuccessStatus._CREATED, ReviewConverter.toReviewRegResultDTO(review));
    }
}
