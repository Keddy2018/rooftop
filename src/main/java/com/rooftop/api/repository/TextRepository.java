package com.rooftop.api.repository;

import com.rooftop.api.model.Text;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
    
    Optional<Text> findByHashCode(String hashCode);
    
    Optional<List<Text>> findByChars(int chars, Pageable pageable);
    
}
