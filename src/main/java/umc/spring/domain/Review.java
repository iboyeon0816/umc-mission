package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float score;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setStore(Store store) {
        if (this.store != null) {
            this.store.getReviews().remove(this);
        }

        this.store = store;
        store.getReviews().add(this);
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getReviews().remove(this);
        }

        this.member = member;
        member.getReviews().add(this);
    }
}
