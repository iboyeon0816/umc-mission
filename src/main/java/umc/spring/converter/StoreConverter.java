package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO.RegDTO;
import umc.spring.web.dto.StoreResponseDTO.RegResultDTO;

public class StoreConverter {

    public static Store toStore(RegDTO regDTO) {
        return Store.builder()
                .name(regDTO.getName())
                .address(regDTO.getAddress())
                .build();
    }

    public static RegResultDTO toRegResultDTO(Store store) {
        return RegResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
