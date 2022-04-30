package The.Geeks.ResmProject.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.Entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
