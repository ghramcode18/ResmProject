package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Image;
@Repository
public interface FileDBRepository  extends JpaRepository<Image, String>{
}
