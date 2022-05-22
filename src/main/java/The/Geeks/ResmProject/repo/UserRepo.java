package collage.project1.resm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import collage.project1.resm.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    User findByUsername (String usename);
}
