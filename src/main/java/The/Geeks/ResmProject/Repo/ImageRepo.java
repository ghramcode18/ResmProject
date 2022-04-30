package The.Geeks.ResmProject.Repo;

    
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {

}
