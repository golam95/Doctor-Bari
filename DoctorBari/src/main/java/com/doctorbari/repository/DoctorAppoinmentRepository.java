package com.doctorbari.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doctorbari.dto.DoctorAppoinmentDtoList;
import com.doctorbari.model.DoctorAppoinment;

@Repository
public interface DoctorAppoinmentRepository extends JpaRepository<DoctorAppoinment, Long>{

	DoctorAppoinment findByBookTimeAndBookDate(String time,Date bookDate);
	
	DoctorAppoinment findByapId(Long appId);
	
	@Query(value="SELECT \r\n" + 
			"d.id,\r\n" + 
			"u.firstname,\r\n" + 
			"d.bookingtime as bTime,\r\n" + 
			"d.bookingdate as bDate,\r\n" + 
			"d.price as bPrice,\r\n" + 
			"d.status as bStatus,\r\n" + 
			"d.tran_id as bkashId\r\n" + 
			"FROM doctor_appoinment d\r\n" + 
			"LEFT JOIN user u ON d.u_id=u.id",nativeQuery=true)
	List<DoctorAppoinmentDtoList> listOfDoctorAppoinment();
	
	
	@Query(value="SELECT \r\n" + 
			"d.id,\r\n" + 
			"u.firstname,\r\n" + 
			"d.bookingtime as bTime,\r\n" + 
			"d.bookingdate as bDate,\r\n" + 
			"d.price as bPrice,\r\n" + 
			"d.meeting as bMeeting,\r\n" + 
			"d.file_path as bFilePath,\r\n" + 
			"d.status as bStatus,\r\n" + 
			"d.tran_id as bkashId,\r\n" + 
			"c.name as diaCenter,\r\n" + 
			"d.remark,\r\n" + 
			"d.username\r\n" + 
			"FROM doctor_appoinment d\r\n" + 
			"LEFT JOIN user u ON d.u_id=u.id\r\n" + 
			"LEFT JOIN diagnostic c ON d.dia_center=c.id\r\n" + 
			"where d.username=?1",nativeQuery=true)
	List<Object[]> listOfDoctorAppoinmentbyUser(String id);
	

	
	@Query(value="SELECT \r\n" + 
			"d.id,\r\n" + 
			"u.firstname,\r\n" + 
			"d.bookingtime as bTime,\r\n" + 
			"d.bookingdate as bDate,\r\n" + 
			"d.price as bPrice,\r\n" + 
			"d.meeting as bMeeting,\r\n" + 
			"d.file_path as bFilePath,\r\n" + 
			"d.status as bStatus,\r\n" + 
			"d.tran_id as bkashId,\r\n" + 
			"c.name as diaCenter,\r\n" + 
			"d.remark,\r\n" + 
			"d.username\r\n" + 
			"FROM doctor_appoinment d\r\n" + 
			"LEFT JOIN user u ON d.u_id=u.id\r\n" + 
			"LEFT JOIN diagnostic c ON d.dia_center=c.id\r\n" + 
			"where d.u_id=?1 and d.status not in(3)",nativeQuery=true)
	List<Object[]> listOfDoctorAppoinmentbyDoctor(Long id);
	
	
	@Query(value="SELECT \r\n" + 
			"d.id,\r\n" + 
			"u.firstname,\r\n" + 
			"d.bookingtime as bTime,\r\n" + 
			"d.bookingdate as bDate,\r\n" + 
			"d.price as bPrice,\r\n" + 
			"d.meeting as bMeeting,\r\n" + 
			"d.file_path as bFilePath,\r\n" + 
			"d.status as bStatus,\r\n" + 
			"d.tran_id as bkashId,\r\n" + 
			"c.name as diaCenter,\r\n" + 
			"d.remark,\r\n" + 
			"d.username\r\n" + 
			"FROM doctor_appoinment d\r\n" + 
			"LEFT JOIN user u ON d.u_id=u.id\r\n" + 
			"LEFT JOIN diagnostic c ON d.dia_center=c.id",nativeQuery=true)
	List<Object[]> listOfDoctorAppoinmentbyAdmin();

	
	
}
