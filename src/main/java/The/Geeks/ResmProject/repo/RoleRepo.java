package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long>{
    Role findByroleId (Long roleId);
}
