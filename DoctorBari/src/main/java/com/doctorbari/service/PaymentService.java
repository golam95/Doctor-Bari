package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.AccountReportDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Payment;

public interface PaymentService {

	List<Payment> paymentList(Long userId);
	
	List<AccountReportDto> accountReport(Long id);
	
	List<AccountReportDto> accountReportList();
	
	CommonMsg deletePayment(Long id);
}
