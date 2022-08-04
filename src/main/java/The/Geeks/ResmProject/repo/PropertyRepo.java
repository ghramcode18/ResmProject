package The.Geeks.ResmProject.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    List<Property> findByPrice(Float price);
    
    Property findByPropertyId(long id);

    List<Property> findBySpace(Float space);
    
    List<Property> findByNumRooms(Integer numRooms);
    
    List<Property> findByNumBathrooms(Integer numBathrooms);
    
    List<Property> findByNumStoreys(Integer numStoreys);

    @Query(value = "SELECT * FROM `properties` WHERE date_added=?1", nativeQuery = true)
    List<Property> findByDateAdded(LocalDateTime date);

    

    @Query(value = "select *  FROM properties p inner JOIN properties_category PIM"+
    " ON p.property_categoryid= PIM.property_category_id WHERE p.property_categoryid=?1", nativeQuery = true)
    List<Property> findByPropertyCategoryId(Long id);

    void save(Optional<Property> newProperty);

}
