package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureLike;
import moine.domain.repository.LectureLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    @Autowired
    private LectureLikeRepository lectureLikeRepository;

    private final CrawlingService crawlingService;
    private final UserService userService;

    // lecture_like 디비에 어떤 userid가 어떤 강의를 찜했는지 저장
    public LectureLike saveLike(Long lectureId, Long userId) {

        LectureLike lecture_liked = new LectureLike();

        LectureCrawling lecture = crawlingService.getLectureById(lectureId);
        lecture_liked.setLectureCrawling(lecture);
        lecture_liked.setCategoryName(lecture.getCategoryName());
        lecture_liked.setUser(userService.getUser(userId));

        this.lectureLikeRepository.save(lecture_liked);  // 첫번째 강의 저장

        return lecture_liked;

    }
}
