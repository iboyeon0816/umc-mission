package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO.StoreSaveDTO;
import umc.spring.web.dto.StoreResponseDTO.SaveStoreResultDTO;

public class StoreConverter {

    public static Store toStore(StoreSaveDTO storeSaveDTO) {
        return Store.builder()
                .name(storeSaveDTO.getName())
                .address(storeSaveDTO.getAddress())
                .build();
    }

    public static SaveStoreResultDTO toRegResultDTO(Store store) {
        return SaveStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
