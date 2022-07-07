package The.Geeks.ResmProject.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.domain.PropertyImage;

@Repository
public interface PropertyImageRepo extends JpaRepository<PropertyImage, Long> {
    // SELECT Customers.CustomerName, Orders.OrderID
    // FROM Customers
    // LEFT JOIN
    // Orders
    // ON Customers.CustomerID=Orders.CustomerID
    // ORDER
    // BY Customers.CustomerName;

    @Query(value = " select *  FROM properties p inner JOIN properties_image PIM ON p.property_id= PIM.property_id WHERE p.property_id=?1", nativeQuery = true)
    PropertyImage findByPropertyId(Long propertyid);

    /*
     * 
     * @Query(value = "select * from communities where name like %:keyword% or
     * description like %:keyword%", nativeQuery = true)
     * List<CommunityEntity> findByNameLike(@Param("keyword") String
     * value);
     * 
     * @Query("SELECT u FROM User u WHERE u.status = ?1")
     * User findUserByStatus(Integer status);
     * 
     * @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name =
     * ?2")
     * User findUserByStatusAndName(Integer status, String name);
     */
}
