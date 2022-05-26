package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.ImageStatus;
@Repository
public interface ImageStatusRepo extends JpaRepository<ImageStatus,Long> {
    
}
