package com.doctorbari.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.doctorbari.dto.AccountReportDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Payment;
import com.doctorbari.repository.PaymentRepository;
import com.doctorbari.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Payment> paymentList(Long userId) {
		return paymentRepository.findByuserId(userId);
	}

	@Override
	public List<AccountReportDto> accountReport(Long id) {
		return paymentRepository.accountReport(id);
	}

	@Override
	public List<AccountReportDto> accountReportList() {
		return paymentRepository.accountReportList();
	}

	@Override
	public CommonMsg deletePayment(Long id) {
		CommonMsg commonmsg = new CommonMsg();
		paymentRepository.deleteById(id);
		commonmsg.setMsgCode("200");
		return commonmsg;
	}

}
