package com.doctorbari.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.doctorbari.dto.AmbBookingDto;
import com.doctorbari.dto.BloodBookingDto;
import com.doctorbari.model.BloodBooking;

@Repository
public interface BloodBookingRepository extends JpaRepository<BloodBooking, Long>{

	BloodBooking findByBookTimeAndBookDate(String bookTime,Date bookDate);
	
	BloodBooking findByBookId(Long id);
	
	@Query(value="SELECT D.id,B.name,D.bookingtime,D.bookingdate,D.status \r\n" + 
			"FROM BLOOD_BOOK D	 \r\n" + 
			"LEFT JOIN BLOOD B ON D.bloodid=B.id",nativeQuery=true)
	List<BloodBookingDto> listOfBloodBooking();
	
	@Query(value="SELECT D.id,B.name,D.bookingtime,D.bookingdate,D.status \r\n" + 
			"	FROM BLOOD_BOOK D\r\n" + 
			"	LEFT JOIN BLOOD B ON D.bloodid=B.id\r\n" + 
			"    where d.username=?1",nativeQuery=true)
	List<BloodBookingDto> listOfBloodBookingByUserId(String username);
	

	
	
	
}
