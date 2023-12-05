package umc.spring.service.mission;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import umc.spring.repository.MissionRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public boolean exist(Long id) {
        return missionRepository.existsById(id);
    }
}
