package com.doctorbari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.doctorbari.model.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	Contact findByContactId(Long id);
	
}
