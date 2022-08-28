package moine.domain.repository;

import moine.domain.entity.LectureCrawling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureCrawlingRepository extends JpaRepository<LectureCrawling, Long> {
    // search 컬럼 : 강의명, 강사명, 분류, 커리큘럼, 소개
    List<LectureCrawling> findByLectureNameContaining(String lecture_name);
    List<LectureCrawling> findByTeacherNameContaining(String teacher_name);
    List<LectureCrawling> findByCategoryNameContaining(String category_name);
    List<LectureCrawling> findByCurriculumContaining(String curriculum);
    List<LectureCrawling> findByIntroductionContaining(String introduction);


}
