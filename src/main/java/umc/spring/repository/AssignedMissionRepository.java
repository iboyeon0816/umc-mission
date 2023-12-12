package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.AssignedMission;

public interface AssignedMissionRepository extends JpaRepository<AssignedMission, Long> {

    boolean existsByMemberAndMission(Member member, Mission mission);

    Page<AssignedMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
}
