package The.Geeks.ResmProject.Repo;

    
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.proprty_type;

@Repository
public interface proprty_typeRepo extends JpaRepository<proprty_type, Integer> {

}
