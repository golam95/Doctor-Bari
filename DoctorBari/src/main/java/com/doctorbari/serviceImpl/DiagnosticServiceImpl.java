package com.doctorbari.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Blood;
import com.doctorbari.model.Diagnostic;
import com.doctorbari.repository.DiagnosticRepository;
import com.doctorbari.service.DiagnosticService;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {
	
	@Autowired
	private DiagnosticRepository diagnosticRepository;

	@Override
	public CommonMsg saveDiagnostic(InsertDto insertDto) {
		CommonMsg commonMsg = new CommonMsg();
		Diagnostic diagnostic = new Diagnostic();
		if (insertDto.getUpdateId().isEmpty()) {
			Diagnostic checkName = diagnosticRepository.findByName((insertDto.getName()));
			if (checkName != null) {
				commonMsg.setMsgCode("201");
			} else {
				diagnostic.setName(insertDto.getName().toUpperCase());
				diagnostic.setDate(new Date());
				commonMsg.setMsgCode("200");
				diagnosticRepository.save(diagnostic);
			}
		} else {
			diagnostic.setDiaId(Long.parseLong((insertDto.getUpdateId())));
			diagnostic.setName(insertDto.getName().toUpperCase());
			diagnostic.setDate(new Date());
			commonMsg.setMsgCode("199");
			diagnosticRepository.save(diagnostic);
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deleteDiagnostic(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		diagnosticRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public List<Diagnostic> listOfDiagnostic() {
		return diagnosticRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

}
