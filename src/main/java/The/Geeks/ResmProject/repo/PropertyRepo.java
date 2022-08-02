package The.Geeks.ResmProject.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    List<Property> findByPrice(Float price);

    /*
     * @Query("SELECT r FROM Employee r WHERE r.empId = ?1)
     * Employee getMyEmployee(long id);
     */
    Property findByPropertyId(long id);

    List<Property> findBySpace(Float space);
    
    List<Property> findByNumRooms(Integer numRooms);
    
    List<Property> findByNumBathrooms(Integer numBathrooms);
    
    List<Property> findByNumStoreys(Integer numStoreys);
    
    List<Property> findByPropertyCategory(Integer propertyCategory);

    void save(Optional<Property> newProperty);

}
