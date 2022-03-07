package com.doctorbari.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doctorbari.dto.ContactDto;
import com.doctorbari.dto.PersonalMsgDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Contact;
import com.doctorbari.model.PersonalMsg;
import com.doctorbari.repository.PersonalMsgRepository;
import com.doctorbari.service.PersonalMsgService;

@Service
public class PersonalMsgServiceImpl  implements PersonalMsgService{

	@Autowired
	private PersonalMsgRepository personalMsgRepository;

	@Override
	public CommonMsg savePersonalMsg(ContactDto contactDto,String username) {
		CommonMsg commonMsg=new CommonMsg();
		try {
			PersonalMsg personalMsg=new PersonalMsg();
			personalMsg.setUsername(username);
			personalMsg.setMessage(contactDto.getUserMessage());
			personalMsg.setStatus("0");
			personalMsg.setReplyMsg("");
			personalMsg.setDate(new Date());
			personalMsgRepository.save(personalMsg);
            commonMsg.setMsgCode("200");
		}catch(Exception ex) {
			commonMsg.setMsgCode("500");
		}
		return commonMsg;
	}

	@Override
	public CommonMsg upatePersonalMsg(Long id,String msg) {
		PersonalMsg personalMsg=personalMsgRepository.findBypId(id);
		CommonMsg commonMsg=new CommonMsg();
		if(personalMsg!=null) {
			personalMsg.setStatus("1");
			personalMsg.setReplyMsg(msg);
			personalMsg.setDate(new Date());
			personalMsgRepository.save(personalMsg);
			commonMsg.setMsgCode("200");
		}else {
			commonMsg.setMsgCode("500");
		}
		return commonMsg;
	}

	@Override
	public List<PersonalMsgDto> personalMsgList() {
		return personalMsgRepository.personalMsgList();
	}

	@Override
	public CommonMsg deletePersonalMsg(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		personalMsgRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<PersonalMsgDto> personalMsgListById(String username) {
		return personalMsgRepository.personalMsgListById(username);
	}

	@Override
	public long totalMsg() {
		return personalMsgRepository.count();
	}
}
