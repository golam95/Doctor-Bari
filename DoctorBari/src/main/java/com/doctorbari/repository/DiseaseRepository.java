package com.doctorbari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doctorbari.model.Blood;
import com.doctorbari.model.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long>{

	Disease findByName(String name);
}
