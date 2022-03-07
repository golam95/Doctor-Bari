package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.ContactDto;
import com.doctorbari.dto.PersonalMsgDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.PersonalMsg;

public interface PersonalMsgService {
	
	CommonMsg savePersonalMsg(ContactDto contactDto,String username);
	
	CommonMsg upatePersonalMsg(Long id,String msg);
	
	List<PersonalMsgDto> personalMsgList();
	
	CommonMsg deletePersonalMsg(Long id);
	
	List<PersonalMsgDto> personalMsgListById(String username);
	
	long totalMsg();
}
