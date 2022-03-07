package com.doctorbari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doctorbari.model.Blood;
import com.doctorbari.model.Diagnostic;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {

	Diagnostic findByName(String name);
}
