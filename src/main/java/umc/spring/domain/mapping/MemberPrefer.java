package umc.spring.domain.mapping;

import lombok.*;
import umc.spring.domain.FoodType;
import umc.spring.domain.Member;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOOD_TYPE_ID")
    private FoodType foodType;

    public void setMember(Member member) {
        if (this.member != null) {
            member.getMemberPrefers().remove(this);
        }

        this.member = member;
        member.getMemberPrefers().add(this);
    }
}
