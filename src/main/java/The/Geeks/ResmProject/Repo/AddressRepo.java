package The.Geeks.ResmProject.Repo;

    
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}
