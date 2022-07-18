package moine.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureTestRepository extends JpaRepository<LectureTestEntity, Long> {
}
