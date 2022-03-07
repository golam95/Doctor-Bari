package com.doctorbari.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doctorbari.dto.DiseaseSearchDtoList;
import com.doctorbari.dto.DoctorTBListDto;
import com.doctorbari.model.DoctorDetails;
import com.doctorbari.model.User;

@Repository
public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Long>  {
	
	DoctorDetails findByuserId(Long id);
	
	DoctorDetails findBydoctorId(Long id);
	
	
	DoctorDetails deleteByUserId(Long id);
	
	
	@Query(value="select u.id,u.firstname,u.email,u.phone,d.image,d.certificate,d.uni_name as uniName,d.experties,d.deg_name as DegNm,e.name as desName,d.price from doctor d \r\n" + 
			"			left join disease e on d.experties=e.id\r\n" + 
			"			left join user u on d.user_id=u.id\r\n" + 
			"			where u.id=?1",nativeQuery=true)
	DiseaseSearchDtoList doctorDetailsList(Long id);
	
	
	@Query(value="SELECT u.id,U.firstname,D.\r\n" + 
			"			uni_name as uniName,D.deg_name as degName,D.experties as desName,D.image,u.email,u.phone,D.certificate,D.price,D.about FROM DOCTOR D\r\n" + 
			"			LEFT JOIN USER U ON D.USER_ID=U.ID\r\n" + 
			"			WHERE d.experties=:name",nativeQuery=true)
	List<DiseaseSearchDtoList> searchDisease(@Param("name") String name);
	
	
	@Query(value="SELECT d.id,U.firstname,D. \r\n" + 
			"uni_name as uniName,D.deg_name as degName,D.experties,D.image,D.certificate,D.price,D.about FROM DOCTOR D\r\n" + 
			"LEFT JOIN USER U ON D.USER_ID=U.ID\r\n" + 
			"WHERE U.rolename='ROLE_DOCTOR'",nativeQuery=true)
	List<DoctorTBListDto> doctorTBList();
	

}
