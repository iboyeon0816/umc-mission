package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.AssignedMission;

public interface AssignedMissionRepository extends JpaRepository<AssignedMission, Long> {

    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
