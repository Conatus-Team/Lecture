package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.dto.RecommendedItemDto;
import moine.domain.dto.RecommendedItemListDto;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureLike;
import moine.domain.entity.LectureRecommend;
import moine.domain.entity.User;
import moine.domain.event.LectureRecommended;
import moine.domain.repository.LectureCrawlingRepository;
import moine.domain.repository.LectureRecommendRepository;
import moine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {
    private final LectureRecommendRepository lectureRecommendRepository;

    private final CrawlingService crawlingService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final LectureCrawlingRepository lectureCrawlingRepository;

    // DB에 추천 강의 저장하기
    public LectureRecommend postRecommend(Long lectureId, Long userId){
        LectureCrawling newLecture = crawlingService.getLectureById(lectureId);
        User newUser = userService.getUserById(userId);

        LectureRecommend recommend = new LectureRecommend();
        recommend.setLectureCrawling(newLecture);
        recommend.setUser(newUser);

        return lectureRecommendRepository.save(recommend);

    }

    // 추천받은 그룹 리스트 저장
    public void saveRecommendedLectureList(RecommendedItemListDto recommendedItemListDto) {
        List<RecommendedItemDto> data = recommendedItemListDto.getData();
        for (RecommendedItemDto item: data
        ) {
            LectureRecommend recommend = new LectureRecommend();
            recommend.setUser(userRepository.findByUserId(item.getUserId()));
            recommend.setLectureCrawling(lectureCrawlingRepository.findByLectureId(item.getLectureId()));
            lectureRecommendRepository.save(recommend);
        }
    }

    // 모든 추천 강의 가져오기
    public List<LectureRecommend> getAllRecommendList() {
        return lectureRecommendRepository.findAll();
    }

    // 사용자별 추천 강의 가져오기
    public List<LectureRecommend> getRecommendListByUserId(Long userId) {
        return lectureRecommendRepository.findByUser(userService.getUserById(userId));
    }

}
