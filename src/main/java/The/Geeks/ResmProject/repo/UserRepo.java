package The.Geeks.ResmProject.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    Optional<User>findByUsername (String username);
}
