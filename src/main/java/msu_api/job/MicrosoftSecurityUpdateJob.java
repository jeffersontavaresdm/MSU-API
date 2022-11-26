package msu_api.job;

import msu_api.api.MicrosoftSecurityUpdateAPI;
import msu_api.entity.MicrosoftSecurityUpdate;
import msu_api.repository.MicrosoftSecurityUpdateRepository;
import msu_api.utils.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MicrosoftSecurityUpdateJob {

  Logger logger = LoggerUtils.loggerFor(this);
  private final MicrosoftSecurityUpdateRepository repository;
  private final MicrosoftSecurityUpdateAPI api;

  public MicrosoftSecurityUpdateJob(
    MicrosoftSecurityUpdateRepository repository,
    MicrosoftSecurityUpdateAPI api
  ) {
    this.repository = repository;
    this.api = api;
  }

  @Scheduled(initialDelay = 1_000, fixedDelay = 300_000)
  public void run() {
    var securityUpdates = api.getSecurityUpdates();

    if (!securityUpdates.isEmpty()) {
      var entityKeys = repository.findAll().stream().map(MicrosoftSecurityUpdate::getKey).toList();

      securityUpdates.forEach(dto -> {
        if (!entityKeys.contains(dto.getId())) {
          logger.info("Saving securityUpdate...");

          var securityUpdate = repository.save(dto.toEntity());

          logger.info("Entity saved!. Entity: {}", securityUpdate);
        } else {
          logger.info("Updating securityUpdate...");

          MicrosoftSecurityUpdate securityUpdate = repository.findByKey(dto.getId());

          var updatedSecurityUpdate = repository.save(securityUpdate.copy(securityUpdate.getId(), dto));

          logger.info("Entity updated successfully! Entity: {}", updatedSecurityUpdate);
        }
      });
    } else {
      logger.info("No Security Updates to create or update");
    }
  }
}