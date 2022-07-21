//package moine.main;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(plugin={"pretty","html:target/cucumber"},
//                features = "src/test/resources/features",
//                extraGlue="moine/common")
//public class TestMain {
//
//
//}
package moine.main;

import java.time.LocalDateTime;

import moine.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootTest
class LectureCrawlingTest {



    @Autowired
    private LectureCrawlingRepository LectureCrawlingRepository;

    @Test
    void testJpa() {

        System.out.println(LocalDateTime.now().toString());
        // 2022-07-21T23:58:13.622693900

        LectureCrawlingEntity lecture1 = new LectureCrawlingEntity();
        lecture1.setLecture_name("Hello Camera");
        lecture1.setCategory_name("picture");
        lecture1.setLecture_url("https://www.dongacc.com/online/course/view.htm?mgt_no=1071");
//        lecture1.setCreateDate(LocalDateTime.now());
        this.LectureCrawlingRepository.save(lecture1);  // 첫번째 강의 저장

        LectureCrawlingEntity lecture2 = new LectureCrawlingEntity();
        lecture2.setLecture_name("Love Coffee");
        lecture2.setCategory_name("food");
        lecture2.setLecture_url("https://www.dongacc.com/online/course/view.htm?mgt_no=3780");
//        lecture1.setCreateDate(LocalDateTime.now());
        this.LectureCrawlingRepository.save(lecture2);  // 두번째 강의 저장


        LectureCrawlingEntity lecture3 = new LectureCrawlingEntity();
        lecture3.setLecture_name("커피 레귤레이션");
        lecture3.setCategory_name("food");
        lecture3.setLecture_url("https://www.dongacc.com/online/course/view.htm?mgt_no=3825");
//        lecture1.setCreateDate(LocalDateTime.now());
        this.LectureCrawlingRepository.save(lecture3);


        LectureCrawlingEntity lecture4 = new LectureCrawlingEntity();
        lecture4.setLecture_name("쉽게 배우는 강아지 아로마 테라피");
        lecture4.setCategory_name("공예");
        lecture4.setLecture_url("https://www.dongacc.com/online/course/view.htm?mgt_no=2726");
//        lecture1.setCreateDate(LocalDateTime.now());
        this.LectureCrawlingRepository.save(lecture4);


        LectureCrawlingEntity lecture5 = new LectureCrawlingEntity();
        lecture5.setLecture_name("영화와 함께 문학 즐기기");
        lecture5.setCategory_name("movie");
        lecture5.setLecture_url("https://www.dongacc.com/online/course/view.htm?mgt_no=0811");
//        lecture1.setCreateDate(LocalDateTime.now());
        this.LectureCrawlingRepository.save(lecture5);





    }
}