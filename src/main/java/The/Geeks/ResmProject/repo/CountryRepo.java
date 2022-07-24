package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country,Long>{
    Country findByName (String name);
}
