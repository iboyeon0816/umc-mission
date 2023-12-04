package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO.SaveStoreDTO;
import umc.spring.web.dto.StoreResponseDTO.SaveStoreResultDTO;

public class StoreConverter {

    public static Store toStore(SaveStoreDTO saveStoreDTO) {
        return Store.builder()
                .name(saveStoreDTO.getName())
                .address(saveStoreDTO.getAddress())
                .build();
    }

    public static SaveStoreResultDTO toSaveStoreResultDTO(Store store) {
        return SaveStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
