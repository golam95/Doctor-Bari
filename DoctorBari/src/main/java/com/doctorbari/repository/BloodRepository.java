package com.doctorbari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.doctorbari.model.Blood;


@Repository
public interface BloodRepository extends JpaRepository<Blood, Long>  {
	Blood findByName(String name);
}
