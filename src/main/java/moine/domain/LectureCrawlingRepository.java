package moine.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureCrawlingRepository extends JpaRepository<LectureCrawlingEntity, Long> {
}
