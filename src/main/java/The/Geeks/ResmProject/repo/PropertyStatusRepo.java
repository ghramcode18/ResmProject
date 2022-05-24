package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.PropertyStatus;

@Repository
public interface PropertyStatusRepo extends JpaRepository<PropertyStatus, Long> {
    
}
