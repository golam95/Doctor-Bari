package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.AmbBookingDto;
import com.doctorbari.dto.InsertBookingDto;
import com.doctorbari.helper.CommonMsg;

public interface AmbBookingService {
	
    CommonMsg saveAmbBooking(InsertBookingDto insertBookingDto,String userName);
	
	CommonMsg deleteAmbBooking(Long id);

	List<AmbBookingDto> listOfAmbBooking();
	
	CommonMsg confirmAmbBooking(Long id);
	
	List<AmbBookingDto> listOfAmbBookingByUserId(String id);

}
