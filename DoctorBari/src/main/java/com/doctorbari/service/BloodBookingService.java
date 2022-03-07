package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.BloodBookingDto;
import com.doctorbari.dto.InsertBookingDto;
import com.doctorbari.helper.CommonMsg;

public interface BloodBookingService {

	CommonMsg saveBloodBooking(InsertBookingDto insertBookingDto,String username);

	CommonMsg deleteBloodBooking(Long id);

	List<BloodBookingDto> listOfBloodBooking();

	CommonMsg confirmBloodBooking(Long id);
	
	List<BloodBookingDto> listOfBloodBookingByUserId(String username);

}
