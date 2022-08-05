package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.UserImage;

@Repository
public interface UserImageRepo extends JpaRepository<UserImage, Long>{
    
}
