package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureSearch;
import moine.domain.entity.User;
import moine.domain.repository.LectureCrawlingRepository;
import moine.domain.repository.LectureSearchRepository;
import moine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    @Autowired
    private LectureCrawlingRepository lectureCrawlingRepository;

    @Autowired
    private LectureSearchRepository lectureSearchRepository;

    private final UserService userService;

    // 키워드를 포함하는 강의를 DB에서 가져오기
    public List<LectureCrawling> search(String keyword) {

        // 검색 컬럼 : 강의명, 강사명, 분류, 커리큘럼, 소개
        List<LectureCrawling> results = lectureCrawlingRepository.findByLectureNameContaining(keyword);
        results.addAll(lectureCrawlingRepository.findByTeacherNameContaining(keyword));
        results.addAll(lectureCrawlingRepository.findByCategoryNameContaining(keyword));
        results.addAll(lectureCrawlingRepository.findByCurriculumContaining(keyword));
        results.addAll(lectureCrawlingRepository.findByIntroductionContaining(keyword));

        return results;
    }

    // lecture_search 디비에 어떤 userid가 어떤 키워드를 검색했는지 저장
    public LectureSearch saveSearch(String keyword, Long userId) {
        User user = userService.getUser(userId);

        LectureSearch search = new LectureSearch();
        search.setKeyword(keyword); // 분류
//        search.setUser(userId);
        search.setUser(user);
        this.lectureSearchRepository.save(search);  // 첫번째 강의 저장

        return search;
    }

}
