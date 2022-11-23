package processoseletivo_seplag.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import processoseletivo_seplag.entity.dto.MicrosoftSecurityUpdateDTO;
import processoseletivo_seplag.service.MicrosoftSecurityUpdateService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MicrosoftSecurityUpdateController {

  private final MicrosoftSecurityUpdateService microsoftSecurityUpdateService;

  MicrosoftSecurityUpdateController(MicrosoftSecurityUpdateService microsoftSecurityUpdateService) {
    this.microsoftSecurityUpdateService = microsoftSecurityUpdateService;
  }

  @GetMapping("/list")
  public List<MicrosoftSecurityUpdateDTO> list() {
    return microsoftSecurityUpdateService.list();
  }
}