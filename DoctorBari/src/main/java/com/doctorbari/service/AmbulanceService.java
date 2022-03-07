package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Ambulance;


public interface AmbulanceService {
	
    CommonMsg saveAmbulance(InsertDto insertDto);
	
	CommonMsg deleteAmbulance(Long id);

	List<Ambulance> listOfAmbulance();
	
	Ambulance findByAmbulanceId(Long id);
}
