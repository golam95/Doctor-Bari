package com.doctorbari.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doctorbari.dto.ContactDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Contact;
import com.doctorbari.repository.ContactRepository;
import com.doctorbari.service.ContactService;


@Service
public class ContactServiceImpl implements  ContactService{
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public CommonMsg saveContact(ContactDto contactDto) {
		CommonMsg commonMsg=new CommonMsg();
		try {
			Contact contact=new Contact();
			contact.setEmail(contactDto.getUserEmail());
			contact.setMessage(contactDto.getUserMessage());
			contact.setName(contactDto.getUserName());
			contact.setStatus("1");
			contact.setSubject(contactDto.getUserSubject());
			contact.setDate(new Date());
			contactRepository.save(contact);
            commonMsg.setMsgCode("200");
		}catch(Exception ex) {
			commonMsg.setMsgCode("500");
		}
		return commonMsg;
	}

	@Override
	public CommonMsg upateContact(Long contactId) {
		Contact contact=contactRepository.findByContactId(contactId);
		CommonMsg commonMsg=new CommonMsg();
		if(contact!=null) {
			contact.setStatus("0");
			contact.setDate(new Date());
			contactRepository.save(contact);
			commonMsg.setMsgCode("200");
		}else {
			commonMsg.setMsgCode("500");
		}
		return commonMsg;
	}

	@Override
	public List<Contact> contactList() {
		return contactRepository.findAll();
	}

	@Override
	public CommonMsg deleteContact(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		contactRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public long totalContact() {
		return contactRepository.count();
	}

}
