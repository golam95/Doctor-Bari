package com.doctorbari.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Blood;
import com.doctorbari.repository.BloodRepository;
import com.doctorbari.service.BloodService;


@Service
public class BloodServiceImpl  implements BloodService{
	
	@Autowired
	private BloodRepository bloodRepository;
	
	
	@Override
	public CommonMsg saveBlood(InsertDto insertDto) {
		CommonMsg commonMsg = new CommonMsg();
		Blood blood = new Blood();
		if (insertDto.getUpdateId().isEmpty()) {
			Blood checkName = bloodRepository.findByName((insertDto.getName().toUpperCase()));
			if (checkName != null) {
				commonMsg.setMsgCode("201");
			} else {
				blood.setName(insertDto.getName().toUpperCase());
				blood.setDate(new Date());
				commonMsg.setMsgCode("200");
				bloodRepository.save(blood);
			}
		} else {
			blood.setBloodId(Long.parseLong((insertDto.getUpdateId())));
			blood.setName(insertDto.getName().toUpperCase());
			blood.setDate(new Date());
			commonMsg.setMsgCode("200");
			bloodRepository.save(blood);
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteBlood(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		bloodRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<Blood> listOfBlood() {
		return bloodRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

}
