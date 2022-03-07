package com.doctorbari.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Ambulance;
import com.doctorbari.repository.AmbulanceRepository;
import com.doctorbari.service.AmbulanceService;

@Service
public class AmbulanceServiceImpl implements AmbulanceService{
	
	@Autowired
	private AmbulanceRepository ambulanceRepository;
	
	
	@Override
	public CommonMsg saveAmbulance(InsertDto insertDto) {
		CommonMsg commonMsg = new CommonMsg();
		Ambulance ambulance = new Ambulance();
		if (insertDto.getUpdateId().isEmpty()) {
			Ambulance checkName = ambulanceRepository.findByName((insertDto.getName().toUpperCase()));
			if (checkName != null) {
				commonMsg.setMsgCode("201");
			} else {
				ambulance.setName(insertDto.getName().toUpperCase());
				ambulance.setPrice(insertDto.getPrice());
				ambulance.setDate(new Date());
				commonMsg.setMsgCode("200");
				ambulanceRepository.save(ambulance);
			}
		} else {
			ambulance.setAmbulanceId(Long.parseLong(insertDto.getUpdateId()));
			ambulance.setName(insertDto.getName().toUpperCase());
			ambulance.setPrice(insertDto.getPrice());
			ambulance.setDate(new Date());
			commonMsg.setMsgCode("199");
			ambulanceRepository.save(ambulance);
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteAmbulance(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		ambulanceRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<Ambulance> listOfAmbulance() {
		return ambulanceRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	@Override
	public Ambulance findByAmbulanceId(Long id) {
		return ambulanceRepository.findByAmbulanceId(id);
	}

}
