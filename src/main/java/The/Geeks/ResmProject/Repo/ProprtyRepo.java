package The.Geeks.ResmProject.Repo;

    
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.Proprty;

@Repository
public interface ProprtyRepo extends JpaRepository<Proprty, Integer> {

}
