package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureLike;
import moine.domain.repository.LectureLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    @Autowired
    private LectureLikeRepository lectureLikeRepository;

    private final CrawlingService crawlingService;
    private final UserService userService;

    // 찜한 목록에 없으면 추가, 있으면 삭제
    public LectureLike likeResult(Long lectureId, Long userId) {
        // DB에 있는지 검사
        List<LectureLike> lectureLike =
                lectureLikeRepository.findByLectureCrawlingAndUser(
                        crawlingService.getLectureById(lectureId),
                        userService.getUser(userId)
                );



        if(lectureLike.isEmpty()){
            // 없으면 새로 추가
            // DB 저장
            LectureLike newLectureLike = new LectureLike();

            LectureCrawling lecture = crawlingService.getLectureById(lectureId);
            newLectureLike.setLectureCrawling(lecture);
            newLectureLike.setCategoryName(lecture.getCategoryName());
            newLectureLike.setUser(userService.getUser(userId));

            this.lectureLikeRepository.save(newLectureLike);

            // lecture_crawling의 user_like_count 증가
            crawlingService.increaseUserLikeCount(lecture);

            
            return newLectureLike;
        }
        else{
            // 있으면 삭제
            lectureLikeRepository.delete(lectureLike.get(0));

            // lecture_crawling 테이블에서 user_like_count 감소
            LectureCrawling lecture = crawlingService.getLectureById(lectureId);
            crawlingService.decreaseUserLikeCount(lecture);

            return lectureLike.get(0);


        }

    }


}
