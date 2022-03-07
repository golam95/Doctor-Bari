package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Blood;

public interface BloodService {
	
	CommonMsg saveBlood(InsertDto insertDto);
	
	CommonMsg deleteBlood(Long id);

	List<Blood> listOfBlood();
	
}
