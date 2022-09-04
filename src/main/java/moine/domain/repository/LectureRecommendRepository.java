package moine.domain.repository;

import moine.domain.entity.LectureLike;
import moine.domain.entity.LectureRecommend;
import moine.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRecommendRepository extends JpaRepository<LectureRecommend, Long> {
    List<LectureRecommend> findByUser(User user);
}
