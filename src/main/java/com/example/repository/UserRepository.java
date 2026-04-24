package com.example.repository;

import com.example.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByPhoneNumber(String phoneNumber);

    @Query("SELECT DISTINCT u FROM Users u " +
            "LEFT JOIN FETCH u.userSkills us " +
            "LEFT JOIN FETCH us.skills s " +
            "WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.skillName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Users> searchAllWithSkills(@Param("keyword") String keyword, Pageable pageable);

}
