package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Disease;

public interface DiseaseService {
	
    CommonMsg saveDisease(InsertDto insertDto);
	
	CommonMsg deleteDisease(Long id);

	List<Disease> listOfDisease();
	
}
