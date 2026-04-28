package com.example.repository;
import com.example.model.SkillType;
import com.example.model.UserSkill;
import com.example.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill,Long> {

    List<UserSkill> findByUsersId(Long usersId);

    boolean existsByUsersIdAndSkillsIdAndSkillType(Long usersId, Long skillsId, SkillType skillType);

    List<UserSkill> findAllByUsersId(Long usersId);

    @Query("select us.users from UserSkill us where us.skills.id = :skillsId and us.skillType='TEACH'")
    List<Users> findTeachersBySkillId(@Param("skillsId") Long skillsId);


    @Query(value = "SELECT DISTINCT u FROM Users u " +
            "LEFT JOIN UserSkill us ON us.users.id = u.id " +
            "LEFT JOIN Skills s ON us.skills.id = s.id " +
            "WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.skillName) LIKE LOWER(CONCAT('%', :keyword, '%'))",



            countQuery = "SELECT COUNT(DISTINCT u) FROM Users u " +
                    "LEFT JOIN UserSkill us ON us.users.id = u.id " +
                    "LEFT JOIN Skills s ON us.skills.id = s.id " +
                    "WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(s.skillName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Users> searchUsersByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
