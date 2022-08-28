package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureLike;
import moine.domain.entity.LectureRecommend;
import moine.domain.entity.User;
import moine.domain.repository.LectureRecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {
    @Autowired
    private LectureRecommendRepository lectureRecommendRepository;

    private final CrawlingService crawlingService;
    private final UserService userService;

    // DB에 추천 강의 저장하기
    public LectureRecommend postRecommend(Long lectureId, Long userId){
        LectureCrawling newLecture = crawlingService.getLectureById(lectureId);
        User newUser = userService.getUserById(userId);

        LectureRecommend recommend = new LectureRecommend();
        recommend.setLectureCrawling(newLecture);
        recommend.setUser(newUser);

        return lectureRecommendRepository.save(recommend);

    }

    // 모든 추천 강의 가져오기
    public List<LectureRecommend> getAllRecommendList() {
        return lectureRecommendRepository.findAll();
    }

}
