package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.City;

@Repository
public interface CityRepo extends JpaRepository<City,Long>{
    City findByName (String name);
  
}
