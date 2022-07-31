package moine.domain.repository;

import moine.domain.entity.LectureSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureSearchRepository extends JpaRepository<LectureSearch, Long> {
}
