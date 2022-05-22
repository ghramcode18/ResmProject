package collage.project1.resm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import collage.project1.resm.domain.Region;

public interface RegionRepo extends JpaRepository<Region,Long>{
    
}
