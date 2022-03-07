package com.doctorbari.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Blood;
import com.doctorbari.model.Disease;
import com.doctorbari.repository.DiseaseRepository;
import com.doctorbari.service.DiseaseService;

@Service 
public class DiseaseServiceImpl implements DiseaseService{

	@Autowired
	private DiseaseRepository diseaseRepository;

	@Override
	public CommonMsg saveDisease(InsertDto insertDto) {
		CommonMsg commonMsg = new CommonMsg();
		Disease disease = new Disease();
		if (insertDto.getUpdateId().isEmpty()) {
			Disease checkName = diseaseRepository.findByName((insertDto.getName().toUpperCase()));
			if (checkName != null) {
				commonMsg.setMsgCode("201");
			} else {
				disease.setName(insertDto.getName().toUpperCase());
				disease.setDate(new Date());
				commonMsg.setMsgCode("200");
				diseaseRepository.save(disease);
			}
		} else {
			disease.setDiseaseId(Long.parseLong((insertDto.getUpdateId())));
			disease.setName(insertDto.getName().toUpperCase());
			disease.setDate(new Date());
			commonMsg.setMsgCode("200");
			diseaseRepository.save(disease);
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteDisease(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		diseaseRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<Disease> listOfDisease() {
		return diseaseRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	
	
	
}
