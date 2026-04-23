package com.example.repository;

import com.example.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

/*
    @Query("select t from Transaction t " +
            "where lower(t.teacher.fullName) like lower(concat('%', :keyword, '%')) " +
            "or lower(t.student.fullName) like lower(concat('%', :keyword, '%') ) ")*/

    @Query("SELECT t FROM Transaction t WHERE " +
            "LOWER(t.student.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.teacher.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Transaction> searchAllHistory(@Param("keyword") String keyword, Pageable pageable);

}
