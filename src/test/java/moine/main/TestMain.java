
package moine.main;

import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureTestEntity;
import moine.domain.entity.User;
import moine.domain.repository.LectureLikeRepository;
import moine.domain.repository.LectureTestRepository;
import moine.domain.repository.UserRepository;
import moine.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.assertEquals;

//@EnableJpaAuditing
@SpringBootTest
class TestMain {


    @Autowired
    private LectureTestRepository lectureTestRepository;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private LectureLikeRepository lectureLikeRepository;


    private final UserService userService = new UserService();

    // 찜하기
    @Test
    void likeJpa(){
        System.out.println("userRepository.findAll() = " + userRepository.findAll());;

//        // TODO : 찜한 강의들 id 가져오기
//        List<LectureCrawling> test = lectureLikeRepository.findLectureCrawlingByUser(userService.getUser(Long.valueOf(1)));
//        test.forEach(
//                LectureCrawling -> System.out.println("LectureCrawling = " + LectureCrawling)
//        );
    }


    // 크롤링


    @Test
    void crawlingJpa() {
        // create
        LectureTestEntity lecture1 = new LectureTestEntity();
        lecture1.setLecturename("안녕");
        lecture1.setCategory("life");
        lecture1.setPrice(15000);
        // lecture1.setCreateDate(LocalDateTime.now());
        this.lectureTestRepository.save(lecture1);  // 첫번째 강의 저장

        LectureTestEntity lecture2 = new LectureTestEntity();
        lecture2.setLecturename("MusicIsMyLife");
        lecture2.setCategory("music");
        // lecture1.setCreateDate(LocalDateTime.now());
        this.lectureTestRepository.save(lecture2);  // 두번째 질문 저장


        // *** read ***
        // findAll
        System.out.println('\n' + "+++ findAll +++");
        List<LectureTestEntity> all = this.lectureTestRepository.findAll();
//        assertEquals(2, all.size());
        System.out.println("size = " + all.size());

        LectureTestEntity q = all.get(0);
        System.out.println("q = " + q);

        String lecture_name = all.get(0).getLecturename();
        System.out.println("lecture_name = " + lecture_name);

        // findById
        System.out.println('\n' + "+++ findById +++");
        Optional<LectureTestEntity> oq = this.lectureTestRepository.findById(Long.valueOf(1));
        if(oq.isPresent()) {
            LectureTestEntity qq = oq.get();
            System.out.println("qq.getLecturename() = " + qq.getLecturename());
//            assertEquals("sbb가 무엇인가요?", qq.getSubject());
        }




        // update


        // delete

    }


    // User


    @Test
    void testUserJpa() {
        // user table 엔티티 생성 테스트
        User userEntity1 = new User();
        userEntity1.setUserName("김지구");
        userEntity1.setUserNickname("노을");
        this.userRepository.save(userEntity1);

    }


}