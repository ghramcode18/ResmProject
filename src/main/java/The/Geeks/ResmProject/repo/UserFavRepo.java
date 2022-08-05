package The.Geeks.ResmProject.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.UserFav;

@Repository
public interface UserFavRepo extends PagingAndSortingRepository<UserFav, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `user_fav` WHERE `propertyid`=?1", nativeQuery = true)
    void deleteByPropertyId(Long id );
}
