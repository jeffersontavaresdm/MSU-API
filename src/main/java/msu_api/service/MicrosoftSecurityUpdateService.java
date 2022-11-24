package msu_api.service;

import org.springframework.stereotype.Service;
import msu_api.entity.MicrosoftSecurityUpdate;
import msu_api.entity.dto.MicrosoftSecurityUpdateDTO;
import msu_api.repository.MicrosoftSecurityUpdateRepository;

import java.util.List;

@Service
public class MicrosoftSecurityUpdateService {

  private final MicrosoftSecurityUpdateRepository microsoftSecurityUpdateRepository;

  MicrosoftSecurityUpdateService(MicrosoftSecurityUpdateRepository microsoftSecurityUpdateRepository) {
    this.microsoftSecurityUpdateRepository = microsoftSecurityUpdateRepository;
  }

  public List<MicrosoftSecurityUpdateDTO> list() {
    return microsoftSecurityUpdateRepository
      .findAll()
      .stream()
      .map(MicrosoftSecurityUpdate::toDTO)
      .toList();
  }
}