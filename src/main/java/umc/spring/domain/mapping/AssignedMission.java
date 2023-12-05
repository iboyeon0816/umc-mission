package umc.spring.domain.mapping;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.Mission;
import umc.spring.domain.Member;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class AssignedMission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean success;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'PROCEEDING'")
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MISSION_ID")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMission(Mission mission) {
        if (this.mission != null) {
            this.mission.getAssignedMissions().remove(this);
        }

        this.mission = mission;
        mission.getAssignedMissions().add(this);
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getAssignedMissions().remove(this);
        }

        this.member = member;
        member.getAssignedMissions().add(this);
    }
}
