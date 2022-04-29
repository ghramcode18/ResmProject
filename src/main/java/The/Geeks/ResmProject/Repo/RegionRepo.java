package The.Geeks.ResmProject.Repo;


import The.Geeks.ResmProject.Entities.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RegionRepo extends JpaRepository <Region, Integer> {
    
}
