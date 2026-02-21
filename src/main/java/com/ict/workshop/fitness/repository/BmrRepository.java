package com.ict.workshop.fitness.repository;

import com.ict.workshop.fitness.model.BmrRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BmrRepository extends JpaRepository<BmrRecord,Integer> {

    List<BmrRecord> findByGender(String gender);

    List<BmrRecord> findByAgeGreaterThan(int age);
}
