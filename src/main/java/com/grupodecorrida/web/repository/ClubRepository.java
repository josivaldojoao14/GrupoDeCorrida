package com.grupodecorrida.web.repository;

import com.grupodecorrida.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByName(String name);
    
    @Query("SELECT c from Club c WHERE c.name LIKE CONCAT('%', :query, '%')")
    List<Club> searchClubs(String query);
}
