package umc.spring.converter;

import umc.spring.domain.FoodType;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPrefers(List<FoodType> foodTypes) {
        return foodTypes.stream()
                .map(foodType -> MemberPrefer.builder()
                                .foodType(foodType)
                                .build())
                .collect(Collectors.toList());
    }
}
