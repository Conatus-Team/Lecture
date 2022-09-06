package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureLike;
import moine.domain.repository.LectureLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    @Autowired
    private LectureLikeRepository lectureLikeRepository;

    private final CrawlingService crawlingService;
    private final UserService userService;

    // 찜하기 추가
    public LectureLike postLike(Long lectureId, Long userId) {
        // DB에 있는지 검사
        List<LectureLike> lectureLike =
                lectureLikeRepository.findByLectureCrawlingAndUser(
                        crawlingService.getLectureById(lectureId),
                        userService.getUserById(userId)
                );

        if(lectureLike.isEmpty()){
            LectureLike newLectureLike = new LectureLike();

            LectureCrawling lecture = crawlingService.getLectureById(lectureId);
            newLectureLike.setLectureCrawling(lecture);
            newLectureLike.setCategoryName(lecture.getCategoryName());
            newLectureLike.setUser(userService.getUserById(userId));

            this.lectureLikeRepository.save(newLectureLike);

            // lecture_crawling의 user_like_count 증가
            crawlingService.increaseUserLikeCount(lecture);

            // 추천목록의 강의를 찜한 경우
            // 찜목록에 강의 추가, 추천 목록에 강의 삭제


            return newLectureLike;
        }
        else {
            // 이미 찜하기한 유저
            return null;
        }

    }

    // 찜하기 삭제
    public LectureLike deleteLike(Long lectureId, Long userId) {
        // DB에 있는지 검사
        List<LectureLike> lectureLike =
                lectureLikeRepository.findByLectureCrawlingAndUser(
                        crawlingService.getLectureById(lectureId),
                        userService.getUserById(userId)
                );
        if(lectureLike.isEmpty()){
            // 값 없음. 삭제 불가
            return null;
        }
        else{
            lectureLikeRepository.delete(lectureLike.get(0));

            // lecture_crawling 테이블에서 user_like_count 감소
            LectureCrawling lecture = crawlingService.getLectureById(lectureId);
            crawlingService.decreaseUserLikeCount(lecture);

            return lectureLike.get(0);
        }

    }


    // 강의 불러올 때 해당 사용자 찜하기 목록에 있는 강의 id 리스트
    public List<Long> likeList(Long userId){

        List<LectureLike> list =
                lectureLikeRepository.findByUser(userService.getUserById(userId));

        List<Long> lecture_id = new ArrayList();
        for(LectureLike lecturelike : list){
            lecture_id.add(lecturelike.getLectureCrawling().getLectureId());
        }


        return lecture_id;


    }

    // 모두 가져오기
    public List<LectureLike> getAllLikeList() {
        return lectureLikeRepository.findAll();
    }

    public List<LectureLike> getLikeListByUserId(long userId){
        return lectureLikeRepository.findByUser(userService.getUserById(userId));
    }

}
