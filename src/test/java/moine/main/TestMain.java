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

import moine.domain.LectureTestEntity;
import moine.domain.LectureTestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootTest
class TestMain {



    @Autowired
    private LectureTestRepository lectureTestRepository;

    @Test
    void testJpa() {
        LectureTestEntity lecture1 = new LectureTestEntity();
        lecture1.setLecturename("안녕");
        lecture1.setCategory("life");
        lecture1.setPrice(15000);
//        lecture1.setCreateDate(LocalDateTime.now());
        this.lectureTestRepository.save(lecture1);  // 첫번째 강의 저장

        LectureTestEntity lecture2 = new LectureTestEntity();
        lecture2.setLecturename("MusicIsMyLife");
        lecture2.setCategory("music");
//        lecture1.setCreateDate(LocalDateTime.now());
        this.lectureTestRepository.save(lecture2);  // 두번째 질문 저장

    }
}