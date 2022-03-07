package com.doctorbari.repository;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doctorbari.dto.AmbBookingDto;
import com.doctorbari.model.AmbBooking;


@Repository
public interface AmbBookingRepository extends JpaRepository<AmbBooking, Long> {

	AmbBooking findByBookTimeAndBookDate(String time,Date bookDate);
	
	AmbBooking findByAmbookId(Long id);
	
	@Query(value="SELECT b.id,B.ambid,A.name,B.bookingtime,B.bookingdate,a.price,B.status FROM AMB_BOOK B	\r\n" + 
			"LEFT JOIN AMBULANCE A ON B.ambid=A.id",nativeQuery=true)
	List<AmbBookingDto> listOfAmbBooking();
	
	
	@Query(value="SELECT b.id,B.ambid,A.name,B.bookingtime,B.bookingdate,a.price,B.status FROM AMB_BOOK B\r\n" + 
			"LEFT JOIN AMBULANCE A ON B.ambid=A.id\r\n" + 
			"where B.username=?1",nativeQuery=true)
	List<AmbBookingDto> listOfAmbBookingByUserId(String id);

}
