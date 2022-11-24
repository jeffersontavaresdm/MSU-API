package msu_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import msu_api.entity.dto.MicrosoftSecurityUpdateDTO;
import msu_api.service.MicrosoftSecurityUpdateService;

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