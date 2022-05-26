package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.PropertyImage;

@Repository
public interface PropertyImageRepo extends JpaRepository <PropertyImage,Long>{
    
}
