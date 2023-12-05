package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.AssignedMission;

public interface AssignedMissionRepository extends JpaRepository<AssignedMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);
}
