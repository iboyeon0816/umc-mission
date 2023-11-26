package umc.spring.service.TempService;

import org.springframework.stereotype.Service;
import umc.spring.apiPayload.status.ErrorStatus;
import umc.spring.exception.ex.TempException;

@Service
public class TempQueryServiceImpl implements TempQueryService {
    @Override
    public void checkFlag(Integer flag) {
        if (flag == 1) {
            throw new TempException(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
