package moine.domain.repository;

import moine.domain.entity.LectureTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureTestRepository extends JpaRepository<LectureTestEntity, Long> {
    LectureTestEntity findByLecturename(String lecture_name);
    LectureTestEntity findByCategory(String category);

}
