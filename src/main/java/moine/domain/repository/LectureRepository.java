package moine.domain.repository;

import moine.domain.entity.Lecture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="lectures", path="lectures")
public interface LectureRepository extends PagingAndSortingRepository<Lecture, Long>{


}
