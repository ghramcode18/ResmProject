package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.UserFav;

@Repository
public interface UserFavRepo extends JpaRepository<UserFav, Long> {
    
}
