package com.doctorbari.service;

import java.util.List;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Diagnostic;

public interface DiagnosticService {
	
	CommonMsg saveDiagnostic(InsertDto insertDto);
	
	CommonMsg deleteDiagnostic(Long id);

	List<Diagnostic> listOfDiagnostic();

}
