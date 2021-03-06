package py.org.fundacionparaguaya.pspserver.network.services;

import java.util.List;

import py.org.fundacionparaguaya.pspserver.network.dtos.ApplicationDTO;



public interface ApplicationService {

	ApplicationDTO updateApplication(Long applicationId, ApplicationDTO application);

	ApplicationDTO addApplication(ApplicationDTO application);
	
	ApplicationDTO getApplicationById(Long applicationId);
	
	List<ApplicationDTO> getAllApplications();
	
	void deleteApplication(Long applicationId);
	
}
