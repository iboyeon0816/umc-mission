package umc.spring.service.store;

import umc.spring.domain.Store;

import static umc.spring.web.dto.StoreRequestDTO.*;

public interface StoreCommandService {

    Store saveStore(SaveStoreDTO saveStoreDTO);
}
