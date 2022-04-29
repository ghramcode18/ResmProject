package The.Geeks.ResmProject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
 
}
