package The.Geeks.ResmProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import The.Geeks.ResmProject.domain.Property;

@Repository
public interface RegionRepo extends JpaRepository<Region,Long>{
    Region findByname (String name);
}
