////package moine.main;
////
////import io.cucumber.junit.Cucumber;
////import io.cucumber.junit.CucumberOptions;
////import org.junit.runner.RunWith;
////
////@RunWith(Cucumber.class)
////@CucumberOptions(plugin={"pretty","html:target/cucumber"},
////                features = "src/test/resources/features",
////                extraGlue="moine/common")
////public class TestMain {
////
////
////}
//package moine.main;
//
//import java.time.LocalDateTime;
//
//import moine.domain.entity.LectureCrawling;
//import moine.domain.entity.LectureDetailShow;
//import moine.domain.repository.LectureDetailShowRepository;
//import moine.domain.service.CrawlingService;
//import moine.domain.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
////@EnableJpaAuditing
//@SpringBootTest
//class LectureCrawlingTest {
//
//
//
//    @Autowired
//    private moine.domain.repository.LectureCrawlingRepository LectureCrawlingRepository;
//
//    @Autowired
//    private LectureDetailShowRepository lectureDetailShowRepository;
//
//
//    @Test
//    void testDetailShow() {
//
////        LectureDetailShow lecture = new LectureDetailShow();
////        lecture.setLectureCrawling(LectureCrawlingRepository.findById(Long.valueOf(1)).get());
//        System.out.println(LectureCrawlingRepository.findById(Long.valueOf(1)).get());
//    }
//
//    @Test
//    void testJpa() {
//
//        System.out.println(LocalDateTime.now().toString());
//        // 2022-07-21T23:58:13.622693900
//
//        LectureCrawling lecture1 = new LectureCrawling();
//        lecture1.setLectureName("Hello Camera");
//        lecture1.setCategoryName("picture");
//        lecture1.setLectureUrl("https://www.dongacc.com/online/course/view.htm?mgt_no=1071");
////        lecture1.setCreateDate(LocalDateTime.now());
//        this.LectureCrawlingRepository.save(lecture1);  // 첫번째 강의 저장
//
//        LectureCrawling lecture2 = new LectureCrawling();
//        lecture2.setLectureName("Love Coffee");
//        lecture2.setCategoryName("food");
//        lecture2.setLectureUrl("https://www.dongacc.com/online/course/view.htm?mgt_no=3780");
////        lecture1.setCreateDate(LocalDateTime.now());
//        this.LectureCrawlingRepository.save(lecture2);  // 두번째 강의 저장
//
//
//        LectureCrawling lecture3 = new LectureCrawling();
//        lecture3.setLectureName("커피 레귤레이션");
//        lecture3.setCategoryName("food");
//        lecture3.setLectureUrl("https://www.dongacc.com/online/course/view.htm?mgt_no=3825");
////        lecture1.setCreateDate(LocalDateTime.now());
//        this.LectureCrawlingRepository.save(lecture3);
//
//
//        LectureCrawling lecture4 = new LectureCrawling();
//        lecture4.setLectureName("쉽게 배우는 강아지 아로마 테라피");
//        lecture4.setCategoryName("공예");
//        lecture4.setLectureUrl("https://www.dongacc.com/online/course/view.htm?mgt_no=2726");
////        lecture1.setCreateDate(LocalDateTime.now());
//        this.LectureCrawlingRepository.save(lecture4);
//
//
//        LectureCrawling lecture5 = new LectureCrawling();
//        lecture5.setLectureName("영화와 함께 문학 즐기기");
//        lecture5.setCategoryName("movie");
//        lecture5.setLectureUrl("https://www.dongacc.com/online/course/view.htm?mgt_no=0811");
////        lecture1.setCreateDate(LocalDateTime.now());
//        this.LectureCrawlingRepository.save(lecture5);
//
//
//
//
//
//    }
//}