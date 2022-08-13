package moine.domain.repository;

import moine.domain.entity.LectureCrawling;
import moine.domain.entity.LectureLike;
import moine.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureLikeRepository extends JpaRepository<LectureLike, Long> {
    List<LectureLike> findByLectureCrawlingAndUser (LectureCrawling lectureCrawling, User user);
    List<LectureLike> findByUser(User user);

}
