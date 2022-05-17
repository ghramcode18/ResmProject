package The.Geeks.ResmProject.Repo;

    
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.proprtyType;

@Repository
public interface proprty_typeRepo extends JpaRepository<proprtyType, Integer> {

}
