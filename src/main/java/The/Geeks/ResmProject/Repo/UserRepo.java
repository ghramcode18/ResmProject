package The.Geeks.ResmProject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.User;

@Repository
public interface UserRepo  extends JpaRepository<User,Integer>  {
  //  User  findByUsername(String username);
}
