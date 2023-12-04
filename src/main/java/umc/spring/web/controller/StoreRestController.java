package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apipayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.store.StoreCommandService;
import umc.spring.web.dto.StoreResponseDTO.RegResultDTO;

import javax.validation.Valid;

import static umc.spring.web.dto.StoreRequestDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public ApiResponse<RegResultDTO> register(@RequestBody @Valid StoreDTO storeDTO) {
        Store store = storeCommandService.registerStore(storeDTO);
        return ApiResponse.onSuccess(StoreConverter.toRegResultDTO(store));
    }
}
