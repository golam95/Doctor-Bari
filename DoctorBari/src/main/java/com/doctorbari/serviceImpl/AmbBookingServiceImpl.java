package com.doctorbari.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorbari.dto.AmbBookingDto;
import com.doctorbari.dto.InsertBookingDto;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.AmbBooking;
import com.doctorbari.model.Ambulance;
import com.doctorbari.model.Blood;
import com.doctorbari.repository.AmbBookingRepository;
import com.doctorbari.service.AmbBookingService;

@Service
public class AmbBookingServiceImpl implements AmbBookingService {


	@Autowired
	private AmbBookingRepository ambBookingRepository;
	

	@Override
	public CommonMsg saveAmbBooking(InsertBookingDto insertBookingDto,String userName) {
		CommonMsg commonMsg = new CommonMsg();
		AmbBooking ambulanceBooking = new AmbBooking();
		AmbBooking checkTimeAndDate = ambBookingRepository.findByBookTimeAndBookDate(insertBookingDto.getBookingTime(), insertBookingDto.getBookingDate());
		if (checkTimeAndDate != null) {
			commonMsg.setMsgCode("201");
		} else {
			ambulanceBooking.setAmbulanceId(insertBookingDto.getFkId());
			ambulanceBooking.setBookTime(insertBookingDto.getBookingTime());
			ambulanceBooking.setBookDate(insertBookingDto.getBookingDate());
			ambulanceBooking.setUserName(userName);
			ambulanceBooking.setStatus("0");
			ambulanceBooking.setDate(new Date());
			ambBookingRepository.save(ambulanceBooking);
			commonMsg.setMsgCode("200");
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteAmbBooking(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		ambBookingRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<AmbBookingDto> listOfAmbBooking() {
		return ambBookingRepository.listOfAmbBooking();
	}

	@Override
	public CommonMsg confirmAmbBooking(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		AmbBooking checkTimeAndDate = ambBookingRepository.findByAmbookId(id);
		checkTimeAndDate.setStatus("1");
		ambBookingRepository.save(checkTimeAndDate);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<AmbBookingDto> listOfAmbBookingByUserId(String id) {
	   return ambBookingRepository.listOfAmbBookingByUserId(id);
	}
}
