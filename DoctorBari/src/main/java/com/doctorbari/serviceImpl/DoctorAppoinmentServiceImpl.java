package com.doctorbari.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.doctorbari.dto.ConfirmAppoinmentDto;
import com.doctorbari.dto.DoctorAppoinmentDtoList;
import com.doctorbari.dto.InsertBookingDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.helper.Helper;
import com.doctorbari.model.DoctorAppoinment;
import com.doctorbari.model.Payment;
import com.doctorbari.repository.DoctorAppoinmentRepository;
import com.doctorbari.repository.PaymentRepository;
import com.doctorbari.service.DoctorAppoinmentService;

@Service
public class DoctorAppoinmentServiceImpl implements DoctorAppoinmentService {

	@Autowired
	private DoctorAppoinmentRepository doctorAppoinmentRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private Helper helper;

	@Value("${upload.document_archieve_path}")
	private String upLoadDocument;

	@Override
	public CommonMsg saveDoctorAppoinment(InsertBookingDto saveDto, String username) {
		CommonMsg commonMsg = new CommonMsg();
		DoctorAppoinment doctorAppoinment = new DoctorAppoinment();
		DoctorAppoinment checkTimeAndDate = doctorAppoinmentRepository
				.findByBookTimeAndBookDate(saveDto.getBookingTime(), saveDto.getBookingDate());
		if (checkTimeAndDate != null) {
			commonMsg.setMsgCode("201");
		} else {
			doctorAppoinment.setUserName(username);
			doctorAppoinment.setUserId(saveDto.getFkId());
			doctorAppoinment.setPrice(saveDto.getPrice());
			doctorAppoinment.setTransactionId(saveDto.getTransactionId());
			doctorAppoinment.setBookTime(saveDto.getBookingTime());
			doctorAppoinment.setBookDate(saveDto.getBookingDate());
			doctorAppoinment.setStatus("0");
			doctorAppoinment.setDate(new Date());
			doctorAppoinmentRepository.save(doctorAppoinment);
			commonMsg.setMsgCode("200");
		}
		return commonMsg;
	}

	@Override
	public List<DoctorAppoinmentDtoList> listOfDoctorAppoinment() {
		return doctorAppoinmentRepository.listOfDoctorAppoinment();
	}

	@Override
	public List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyUser(String id) {
		
		List<DoctorAppoinmentDtoList> doctorAppoinmentDtoList = new ArrayList<>();
		for (Object obj[] : doctorAppoinmentRepository.listOfDoctorAppoinmentbyUser(id)) {
			DoctorAppoinmentDtoList doctorAppoinmentDto = new DoctorAppoinmentDtoList();
			doctorAppoinmentDto.setId((BigInteger) obj[0]);
			doctorAppoinmentDto.setFirstname((String) obj[1]);
			doctorAppoinmentDto.setTime((String) obj[2]);
			doctorAppoinmentDto.setDate((Date) obj[3]);
			doctorAppoinmentDto.setPrice((double) obj[4]);
			doctorAppoinmentDto.setMeeting((String) obj[5] == null ? "" : (String) obj[5]);
			doctorAppoinmentDto.setFilepath(
					(String) obj[6] == null ? "" : helper.getEncoded64StringByteArrayPdfFromFile((String) obj[6]));
			doctorAppoinmentDto.setStatus((String) obj[7]);
			doctorAppoinmentDto.setBkashid((String) (obj[8]));
			doctorAppoinmentDto.setDiaCenter((String) (obj[9]));
			doctorAppoinmentDto.setRemark((String) (obj[10]));
			doctorAppoinmentDto.setUsername((String) (obj[11]));
			doctorAppoinmentDtoList.add(doctorAppoinmentDto);
		}
		return doctorAppoinmentDtoList;
	}

	@Override
	public CommonMsg deleteDoctorAppoinment(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		doctorAppoinmentRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyDoctor(Long id) {

		List<DoctorAppoinmentDtoList> doctorAppoinmentDtoList = new ArrayList<>();
		for (Object obj[] : doctorAppoinmentRepository.listOfDoctorAppoinmentbyDoctor(id)) {
			DoctorAppoinmentDtoList doctorAppoinmentDto = new DoctorAppoinmentDtoList();
			doctorAppoinmentDto.setId((BigInteger) obj[0]);
			doctorAppoinmentDto.setFirstname((String) obj[1]);
			doctorAppoinmentDto.setTime((String) obj[2]);
			doctorAppoinmentDto.setDate((Date) obj[3]);
			doctorAppoinmentDto.setPrice((double) obj[4]);
			doctorAppoinmentDto.setMeeting((String) obj[5] == null ? "" : (String) obj[5]);
			doctorAppoinmentDto.setFilepath(
					(String) obj[6] == null ? "" : helper.getEncoded64StringByteArrayPdfFromFile((String) obj[6]));
			doctorAppoinmentDto.setStatus((String) obj[7]);
			doctorAppoinmentDto.setBkashid((String) (obj[8]));
			doctorAppoinmentDto.setDiaCenter((String) (obj[9]));
			doctorAppoinmentDto.setRemark((String) (obj[10]));
			doctorAppoinmentDto.setUsername((String) (obj[11]));
			doctorAppoinmentDtoList.add(doctorAppoinmentDto);
		}
		return doctorAppoinmentDtoList;
	}

	@Override
	public CommonMsg confirmAppoinmentReject(ConfirmAppoinmentDto confirm) {
		CommonMsg commonMsg = new CommonMsg();
		DoctorAppoinment doctorAppoinment = doctorAppoinmentRepository.findByapId(confirm.getAppointId());
		doctorAppoinment.setRemark(confirm.getRemark());
		doctorAppoinment.setStatus("3");
		doctorAppoinmentRepository.save(doctorAppoinment);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public CommonMsg confirmAppoinmentMeeting(ConfirmAppoinmentDto confirm) {
		CommonMsg commonMsg = new CommonMsg();
		Payment payment=new Payment();
		DoctorAppoinment doctorAppoinment = doctorAppoinmentRepository.findByapId(confirm.getAppointId());
		doctorAppoinment.setMeetingLink(confirm.getMeetingLink());
		doctorAppoinment.setStatus("1");
		payment.setAmount(doctorAppoinment.getPrice());
		payment.setUserId(doctorAppoinment.getUserId());
		payment.setDate(new Date());
		doctorAppoinmentRepository.save(doctorAppoinment);
		paymentRepository.save(payment);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public CommonMsg confirmAppoinmentFile(ConfirmAppoinmentDto confirm, MultipartFile file) throws Exception {
		MultipartFile getFile = file;
		String finalNamePhoto = "";
		finalNamePhoto = helper.saveDocument(getFile, Long.toString(confirm.getAppointId()), upLoadDocument);
		CommonMsg commonMsg = new CommonMsg();
		DoctorAppoinment doctorAppoinment = doctorAppoinmentRepository.findByapId(confirm.getAppointId());
		doctorAppoinment.setFilePath(upLoadDocument + finalNamePhoto);
		doctorAppoinment.setDiaCenter(confirm.getDiaCenter());
		doctorAppoinment.setStatus("2");
		doctorAppoinmentRepository.save(doctorAppoinment);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyAdmin() {
		List<DoctorAppoinmentDtoList> doctorAppoinmentDtoList = new ArrayList<>();
		for (Object obj[] : doctorAppoinmentRepository.listOfDoctorAppoinmentbyAdmin()) {
			DoctorAppoinmentDtoList doctorAppoinmentDto = new DoctorAppoinmentDtoList();
			doctorAppoinmentDto.setId((BigInteger) obj[0]);
			doctorAppoinmentDto.setFirstname((String) obj[1]);
			doctorAppoinmentDto.setTime((String) obj[2]);
			doctorAppoinmentDto.setDate((Date) obj[3]);
			doctorAppoinmentDto.setPrice((double) obj[4]);
			doctorAppoinmentDto.setMeeting((String) obj[5] == null ? "" : (String) obj[5]);
			doctorAppoinmentDto.setFilepath(
					(String) obj[6] == null ? "" : helper.getEncoded64StringByteArrayPdfFromFile((String) obj[6]));
			doctorAppoinmentDto.setStatus((String) obj[7]);
			doctorAppoinmentDto.setBkashid((String) (obj[8]));
			doctorAppoinmentDto.setDiaCenter((String) (obj[9]));
			doctorAppoinmentDto.setRemark((String) (obj[10]));
			doctorAppoinmentDto.setUsername((String) (obj[11]));
			doctorAppoinmentDtoList.add(doctorAppoinmentDto);
		}
		return doctorAppoinmentDtoList;
	}

}
