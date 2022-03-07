package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.ContactDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Contact;

public interface ContactService {
	
	CommonMsg saveContact(ContactDto contactDto);
	
	CommonMsg upateContact(Long contactId);
	
	List<Contact> contactList();
	
	CommonMsg deleteContact(Long id);
	
	long totalContact();
	
}
