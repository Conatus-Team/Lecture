package moine.infra.controller;

import lombok.RequiredArgsConstructor;
import moine.domain.dto.LectureCrawlingVO;
import moine.domain.entity.LectureCrawlingEntity;
import moine.domain.repository.LectureCrawlingRepository;
import moine.domain.service.CrawlingService;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
//@RequestMapping(value="/lecture")
@RequiredArgsConstructor
@RequestMapping(value="/lecturelist")
public class LectureController {
    @Autowired
    private LectureCrawlingRepository lectureCrawlingRepository;

    // 크롤링 서비스
    private final CrawlingService crawlingService;

//    @GetMapping("/all")
    @GetMapping("")
    public List<LectureCrawlingVO> getLectureCrawlingList(HttpServletRequest request) {

        // 크롤링 데이터 불러오기
        List<LectureCrawlingVO> list = crawlingService.getLectureCrawlingList();

        // DB 저장
        for (LectureCrawlingVO data : list){
            LectureCrawlingEntity lecture = new LectureCrawlingEntity();
            lecture.setCategory_name(data.getCategory_name()); // 분류
            lecture.setLecture_name(data.getLecture_name()); // 강의명
            lecture.setTeacher_name(data.getTeacher_name()); // 강사명
            lecture.setLecture_url(data.getLecture_url()); // url
            lecture.setOrigin_like_count(data.getOrigin_like_count()); //추천수
            lecture.setSite_name(data.getSite_name()); // 사이트명


            this.lectureCrawlingRepository.save(lecture);  // 첫번째 강의 저장
        }

        // [{}...{}] 형태로 프론트에 전달
        return list;

    }

}
