package moine.domain.repository;


import moine.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByUserId(long userId);
    User findByUserId(long userId);
}
