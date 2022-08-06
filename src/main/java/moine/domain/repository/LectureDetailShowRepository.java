package moine.domain.repository;

import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureDetailShow;
import moine.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureDetailShowRepository extends JpaRepository<LectureDetailShow, Long> {
    List<LectureDetailShow> findByLectureCrawlingAndUser(LectureCrawling lectureCrawling, User user);

}
