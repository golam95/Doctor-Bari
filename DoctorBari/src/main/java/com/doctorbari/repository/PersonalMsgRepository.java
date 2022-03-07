package com.doctorbari.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.doctorbari.dto.BloodBookingDto;
import com.doctorbari.dto.PersonalMsgDto;
import com.doctorbari.model.PersonalMsg;

@Repository
public interface PersonalMsgRepository extends JpaRepository<PersonalMsg, Long>{
	
	PersonalMsg findBypId(Long id);
	
	@Query(value="SELECT p.id,U.firstname,p.message,p.reply_msg as reMsg,p.date,p.status FROM personal_msg p\r\n" + 
			"LEFT JOIN USER U ON P.username=U.username",nativeQuery=true)
	List<PersonalMsgDto> personalMsgList();
	
	
	@Query(value="SELECT p.id,p.message,p.reply_msg as reMsg,p.date,p.status FROM personal_msg p\r\n" + 
			"where p.username=?1",nativeQuery=true)
	List<PersonalMsgDto> personalMsgListById(String username);

}
