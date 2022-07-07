package The.Geeks.ResmProject.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    // @Query(value = "SELECT propertiesImage.propertyId, images.id FROM propertiesImage LEFT JOIN Orders ON propertiesImage.propertyId= images.propertyId where images.id  = ?1  ORDER BY propertiesImage.propertyId", nativeQuery = true)
    // Optional<Image> findByPropertyImagesId(String id);

    /*
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
