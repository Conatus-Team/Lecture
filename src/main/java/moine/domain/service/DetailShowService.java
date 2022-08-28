package moine.domain.service;

import lombok.RequiredArgsConstructor;
import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureDetailShow;
import moine.domain.repository.LectureDetailShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailShowService {
    @Autowired
    private LectureDetailShowRepository lectureDetailShowRepository;

    private final CrawlingService crawlingService;
    private final UserService userService;



    // lecture_detail_show 디비에 어떤 userid가 어떤 강의를 클릭했는지 저장
    public LectureDetailShow saveLectureDetailShow(Long lectureId, Long userId){

        // DB에 있는지 검사
        List<LectureDetailShow> lectureDetailShow =
                lectureDetailShowRepository.findByLectureCrawlingAndUser(
                        crawlingService.getLectureById(lectureId),
                        userService.getUserById(userId)
                );


        if(lectureDetailShow.isEmpty()){
            // 없으면 새로 추가
            LectureDetailShow newLectureDetailShow = new LectureDetailShow();

            LectureCrawling lecture = crawlingService.getLectureById(lectureId);
            newLectureDetailShow.setLectureCrawling(lecture);
            newLectureDetailShow.setCategoryName(lecture.getCategoryName());
            newLectureDetailShow.setUser(userService.getUserById(userId));
            newLectureDetailShow.setClickCount(1);

            lectureDetailShowRepository.save(newLectureDetailShow);

            return newLectureDetailShow;
        }
        else{
            // 클릭 횟수 증가
            int click_count = lectureDetailShow.get(0).getClickCount() + 1;
            lectureDetailShow.get(0).setClickCount(click_count);

            lectureDetailShowRepository.save(lectureDetailShow.get(0));
        }


        return lectureDetailShow.get(0);
    }

    // 모두 가져오기
    public List<LectureDetailShow> getAllLectureDetailShow(){
        List<LectureDetailShow> lectureDetailShowList = lectureDetailShowRepository.findAll();
        return lectureDetailShowList;
    }




}
