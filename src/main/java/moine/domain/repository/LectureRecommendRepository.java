package moine.domain.repository;

import moine.domain.entity.LectureRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRecommendRepository extends JpaRepository<LectureRecommend, Long> {
}
