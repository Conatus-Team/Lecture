package moine.domain.service.kafka;

import moine.domain.entity.LectureDetailShow;
import moine.domain.event.LectureDetailShown;
import moine.domain.repository.LectureDetailShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {
    // 카프카로 이벤트 발송하는 서비스

    @Autowired
    private LectureDetailShowRepository lectureDetailShowRepository;

    // 사용자가 클릭한 강의 자세히보기 리스트 발송
    public void publishLectureDetailShownEvent() {
        List<LectureDetailShow> detailShowList = lectureDetailShowRepository.findAll();

        LectureDetailShown lectureDetailShown = new LectureDetailShown(detailShowList);
        lectureDetailShown.publishAfterCommit();

    }

    // 사용자가 찜한 강의 리스트 발송
    public void publishLectureLikedEvent() {

    }


}
