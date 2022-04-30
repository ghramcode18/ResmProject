package The.Geeks.ResmProject.Repo;

    
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.Notification;

@Repository
public interface NotificationsRepo extends JpaRepository<Notification, Integer> {

}
