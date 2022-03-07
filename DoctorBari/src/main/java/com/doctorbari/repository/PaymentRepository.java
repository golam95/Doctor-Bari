package com.doctorbari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doctorbari.dto.AccountReportDto;
import com.doctorbari.dto.BloodBookingDto;
import com.doctorbari.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

	List<Payment> findByuserId(Long userId);
	
	@Query(value="select p.id,u.firstname as name,p.date,p.amount from payment p \r\n" + 
			"left join user u on p.uid=u.id\r\n" + 
			"where p.uid=?1",nativeQuery=true)
	List<AccountReportDto> accountReport(Long id);
	

	@Query(value="select p.id,u.firstname as name,p.date,p.amount from payment p \r\n" + 
			"left join user u on p.uid=u.id",nativeQuery=true)
	List<AccountReportDto> accountReportList();

}
