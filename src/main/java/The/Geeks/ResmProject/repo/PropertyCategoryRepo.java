package The.Geeks.ResmProject.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.PropertyCategory;

@Repository
public interface PropertyCategoryRepo  extends JpaRepository<PropertyCategory,Long>{
    PropertyCategory findByCategory(String category);
    Optional<PropertyCategory> findById(Long id);
}
