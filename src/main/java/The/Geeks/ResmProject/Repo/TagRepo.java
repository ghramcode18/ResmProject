package The.Geeks.ResmProject.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.Tag;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer> {

}
