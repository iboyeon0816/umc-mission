package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO.StoreDTO;
import umc.spring.web.dto.StoreResponseDTO.RegResultDTO;

public class StoreConverter {

    public static Store toStore(StoreDTO storeDTO) {
        return Store.builder()
                .name(storeDTO.getName())
                .address(storeDTO.getAddress())
                .build();
    }

    public static RegResultDTO toRegResultDTO(Store store) {
        return RegResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
