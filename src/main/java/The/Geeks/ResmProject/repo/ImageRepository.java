package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Image;
@Repository
public interface ImageRepository  extends JpaRepository<Image, String>{

}
