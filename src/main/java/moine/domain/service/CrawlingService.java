package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.component.JsoupComponent;
import moine.domain.dto.LectureCrawlingVO;
import moine.domain.entity.LectureCrawling;
import moine.domain.repository.LectureCrawlingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingService {
    @Autowired
    private LectureCrawlingRepository lectureCrawlingRepository;

    private final JsoupComponent jsoupComponent;

    // 크롤링 실행
    public List<LectureCrawlingVO> executeCrawlingList() {
        return jsoupComponent.getLectureCrawlingList();
    }

    // DB에서 가져오기
    public List<LectureCrawling> getLectureCrawlingList() {
        // 크롤링 데이터 불러오기
        List<LectureCrawling> list = lectureCrawlingRepository.findAll();

        return list;

    }


    // DB 저장
    public List<LectureCrawlingVO> saveLectureCrawlingList(){

        // 크롤링 데이터 불러오기
        List<LectureCrawlingVO> list = executeCrawlingList();

        // DB 저장
        for (LectureCrawlingVO data : list){
            LectureCrawling lecture = new LectureCrawling();
            lecture.setCategoryName(data.getCategoryName()); // 분류
            lecture.setLectureName(data.getLectureName()); // 강의명
            lecture.setTeacherName(data.getTeacherName()); // 강사명
            lecture.setLectureUrl(data.getLectureUrl()); // url
            lecture.setOriginLikeCount(data.getOriginLikeCount()); //추천수
            lecture.setSiteName(data.getSiteName()); // 사이트명


            this.lectureCrawlingRepository.save(lecture);  // 첫번째 강의 저장
        }

        return list;
    }

    // lecutre_id에 해당하는 lecture 반환
    public LectureCrawling getLectureById(Long lectureId){
        LectureCrawling lecture = lectureCrawlingRepository.findById(lectureId).get();
        return lecture;
    }

}
