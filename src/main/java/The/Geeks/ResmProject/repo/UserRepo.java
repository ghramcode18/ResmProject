package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    User findByUsername (String usename);
}
