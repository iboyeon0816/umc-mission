package umc.spring.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apipayload.status.ErrorStatus;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.FoodType;
import umc.spring.domain.Store;
import umc.spring.exception.ex.FoodTypeException;
import umc.spring.repository.FoodTypeRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO.SaveStoreDTO;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final FoodTypeRepository foodTypeRepository;

    @Override
    public Store saveStore(SaveStoreDTO saveStoreDTO) {

        Store store = StoreConverter.toStore(saveStoreDTO);

        FoodType foodType = foodTypeRepository.findById(saveStoreDTO.getFoodTypeId())
                .orElseThrow(() -> new FoodTypeException(ErrorStatus.FOOD_TYPE_NOT_FOUND));

        store.setFoodType(foodType);

        return storeRepository.save(store);
    }
}
