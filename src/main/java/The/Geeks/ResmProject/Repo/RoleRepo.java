package The.Geeks.ResmProject.Repo;

    
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
