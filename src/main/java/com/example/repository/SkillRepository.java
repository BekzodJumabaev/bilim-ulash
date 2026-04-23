package com.example.repository;

import com.example.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skills, Long> {

    @Override
    Optional<Skills> findById(Long aLong);
}
