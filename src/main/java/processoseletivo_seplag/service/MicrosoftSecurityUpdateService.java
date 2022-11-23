package processoseletivo_seplag.service;

import org.springframework.stereotype.Service;
import processoseletivo_seplag.entity.MicrosoftSecurityUpdate;
import processoseletivo_seplag.entity.dto.MicrosoftSecurityUpdateDTO;
import processoseletivo_seplag.repository.MicrosoftSecurityUpdateRepository;

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