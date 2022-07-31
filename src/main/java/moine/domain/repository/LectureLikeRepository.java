package moine.domain.repository;

import moine.domain.entity.LectureLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureLikeRepository extends JpaRepository<LectureLike, Long> {
}
