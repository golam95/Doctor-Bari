package com.doctorbari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.doctorbari.model.Ambulance;

@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, Long>{
	
	Ambulance findByName(String name);
	
	Ambulance findByAmbulanceId(Long id);
}
