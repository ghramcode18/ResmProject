package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
}
