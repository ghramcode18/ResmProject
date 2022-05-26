package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    
}
