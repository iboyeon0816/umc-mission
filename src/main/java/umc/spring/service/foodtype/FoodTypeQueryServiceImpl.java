package umc.spring.service.foodtype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.FoodTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodTypeQueryServiceImpl implements FoodTypeQueryService {

    private final FoodTypeRepository foodTypeRepository;

    @Override
    public boolean allExist(List<Long> ids) {
        return ids.stream()
                .allMatch(foodTypeRepository::existsById);
    }

    @Override
    public boolean exist(Long id) {
        return foodTypeRepository.existsById(id);
    }
}
