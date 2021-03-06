package py.org.fundacionparaguaya.pspserver.network.services.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import py.org.fundacionparaguaya.pspserver.common.exceptions.UnknownResourceException;
import py.org.fundacionparaguaya.pspserver.network.dtos.ApplicationDTO;
import py.org.fundacionparaguaya.pspserver.network.entities.ApplicationEntity;
import py.org.fundacionparaguaya.pspserver.network.mapper.ApplicationMapper;
import py.org.fundacionparaguaya.pspserver.network.repositories.ApplicationRepository;
import py.org.fundacionparaguaya.pspserver.network.services.ApplicationService;



@Service
public class ApplicationServiceImpl implements ApplicationService {

	private Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	private ApplicationRepository applicationRepository;
	
	private final ApplicationMapper applicationMapper;

	 
	public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
		this.applicationRepository = applicationRepository;
		this.applicationMapper = applicationMapper;
	}
	

	@Override
	public ApplicationDTO updateApplication(Long applicationId, ApplicationDTO applicationDTO) {
		checkArgument(applicationId > 0, "Argument was %s but expected nonnegative", applicationId);

		return Optional.ofNullable(applicationRepository.findOne(applicationId))
                .map(application -> {
                    BeanUtils.copyProperties(applicationDTO, application);
                    LOG.debug("Changed Information for Application: {}", application);
                    return application;
                })
                .map(applicationMapper::entityToDto)
                .orElseThrow(() -> new UnknownResourceException("Application does not exist"));
	}

	
	@Override
	public ApplicationDTO addApplication(ApplicationDTO applicationDTO) {
		ApplicationEntity application = new ApplicationEntity();
		BeanUtils.copyProperties(applicationDTO, application);
		ApplicationEntity newApplication = applicationRepository.save(application);
		return applicationMapper.entityToDto(newApplication);
	}

	
	@Override
	public ApplicationDTO getApplicationById(Long applicationId) {
		checkArgument(applicationId > 0, "Argument was %s but expected nonnegative", applicationId);

        return Optional.ofNullable(applicationRepository.findOne(applicationId))
                .map(applicationMapper::entityToDto)
                .orElseThrow(() -> new UnknownResourceException("Application does not exist"));
	}

	
	@Override
	public List<ApplicationDTO> getAllApplications() {
		List<ApplicationEntity> applications = applicationRepository.findAll();
		return applicationMapper.entityListToDtoList(applications);
	}

	
	@Override
	public void deleteApplication(Long applicationId) {
		checkArgument(applicationId > 0, "Argument was %s but expected nonnegative", applicationId);

        Optional.ofNullable(applicationRepository.findOne(applicationId))
                .ifPresent(application -> {
                	applicationRepository.delete(application);
                    LOG.debug("Deleted Application: {}", application);
                });
	} 
	 

}
