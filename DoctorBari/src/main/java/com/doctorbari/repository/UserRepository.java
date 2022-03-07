package com.doctorbari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doctorbari.dto.AmbBookingDto;
import com.doctorbari.dto.DoctorDropDownDto;
import com.doctorbari.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);
	
	User findByemail(String email);
	
	User findByphone(String phone);
	
	List<User> findByrolename(String roleName);
	
	User findByUserid(Long id);
	
	long countByrolename(String roleName);

	@Query(value="SELECT B.ambid,A.name,B.bookingtime,B.bookingdate,a.price FROM AMB_BOOK B	\r\n" + 
			"LEFT JOIN AMBULANCE A ON B.ambid=A.id",nativeQuery=true)
	List<User> dropDownDoctorList();
	
	@Query(value="SELECT u.id,u.firstname FROM USER u\r\n" + 
			"			WHERE u.id NOT IN (SELECT d.user_id  FROM doctor d)\r\n" + 
			"			AND u.rolename='ROLE_DOCTOR'",nativeQuery=true)
	List<DoctorDropDownDto> getDoctorList();

}
