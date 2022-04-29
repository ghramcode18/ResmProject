package The.Geeks.ResmProject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.City;

@Repository

public interface CityRepo extends JpaRepository<City, Integer> {
    
}
