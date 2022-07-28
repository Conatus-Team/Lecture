package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.component.JsoupComponent;
import moine.domain.dto.LectureCrawlingVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingService {
    private final JsoupComponent jsoupComponent;

    public List<LectureCrawlingVO> getLectureCrawlingList() {
        return jsoupComponent.getLectureCrawlingList();
    }

}
