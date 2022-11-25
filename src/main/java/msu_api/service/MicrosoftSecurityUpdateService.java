package msu_api.service;

import msu_api.entity.MicrosoftSecurityUpdate;
import msu_api.repository.MicrosoftSecurityUpdateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicrosoftSecurityUpdateService {

  private final MicrosoftSecurityUpdateRepository microsoftSecurityUpdateRepository;

  MicrosoftSecurityUpdateService(MicrosoftSecurityUpdateRepository microsoftSecurityUpdateRepository) {
    this.microsoftSecurityUpdateRepository = microsoftSecurityUpdateRepository;
  }

  public List<MicrosoftSecurityUpdate> list() {
    return microsoftSecurityUpdateRepository.findByOrderByIdAsc();
  }
}