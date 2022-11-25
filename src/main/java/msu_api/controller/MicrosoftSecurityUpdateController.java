package msu_api.controller;

import msu_api.entity.MicrosoftSecurityUpdate;
import msu_api.service.MicrosoftSecurityUpdateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MicrosoftSecurityUpdateController {

  private final MicrosoftSecurityUpdateService microsoftSecurityUpdateService;

  MicrosoftSecurityUpdateController(MicrosoftSecurityUpdateService microsoftSecurityUpdateService) {
    this.microsoftSecurityUpdateService = microsoftSecurityUpdateService;
  }

  @GetMapping("/updates")
  public List<MicrosoftSecurityUpdate> list() {
    return microsoftSecurityUpdateService.list();
  }
}
