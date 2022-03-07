package com.doctorbari.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctorbari.dto.BloodBookingDto;
import com.doctorbari.dto.InsertBookingDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.AmbBooking;
import com.doctorbari.model.BloodBooking;
import com.doctorbari.repository.BloodBookingRepository;
import com.doctorbari.service.BloodBookingService;

@Service
public class BloodBookingServiceImpl implements BloodBookingService{

	@Autowired
	private BloodBookingRepository bloodBookingRepository;

	@Override
	public CommonMsg saveBloodBooking(InsertBookingDto insertBookingDto,String username) {
		CommonMsg commonMsg = new CommonMsg();
		BloodBooking bloodBooking = new BloodBooking();
		//BloodBooking checkTimeAndDate = bloodBookingRepository.findByBookTimeAndBookDate(insertBookingDto.getBookingTime(), insertBookingDto.getBookingDate());
		//if (checkTimeAndDate != null) {
		//	commonMsg.setMsgCode("201");
		//} else {
			bloodBooking.setBloodId(insertBookingDto.getFkId());
			bloodBooking.setUserName(username);
			bloodBooking.setBookTime(insertBookingDto.getBookingTime());
			bloodBooking.setBookDate(insertBookingDto.getBookingDate());
			bloodBooking.setStatus("0");
			bloodBooking.setDate(new Date());
			bloodBookingRepository.save(bloodBooking);
			commonMsg.setMsgCode("200");
		//}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteBloodBooking(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		bloodBookingRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<BloodBookingDto> listOfBloodBooking() {
		return bloodBookingRepository.listOfBloodBooking();
	}

	@Override
	public CommonMsg confirmBloodBooking(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		BloodBooking checkTimeAndDate = bloodBookingRepository.findByBookId(id);
		checkTimeAndDate.setStatus("1");
		bloodBookingRepository.save(checkTimeAndDate);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<BloodBookingDto> listOfBloodBookingByUserId(String username) {
		return bloodBookingRepository.listOfBloodBookingByUserId(username);
	}

}
