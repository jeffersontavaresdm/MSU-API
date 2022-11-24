package msu_api.job;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import msu_api.api.MicrosoftSecurityUpdateAPI;
import msu_api.entity.MicrosoftSecurityUpdate;
import msu_api.repository.MicrosoftSecurityUpdateRepository;
import msu_api.utils.LoggerUtils;

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
    var microsoftSecurityUpdatePayloads = api.updates();

    if (!microsoftSecurityUpdatePayloads.isEmpty()) {
      var entityKeys = repository.findAll().stream().map(MicrosoftSecurityUpdate::getKey).toList();

      microsoftSecurityUpdatePayloads.forEach(dto -> {
        if (!entityKeys.contains(dto.getId())) {
          logger.info("Saving entity...");

          var entity = repository.save(dto.toEntity());

          logger.info("Entity saved!. Entity: {}", entity);
        } else {
          logger.info("Updating entity...");

          MicrosoftSecurityUpdate entity = repository.findByKey(dto.getId());

          var updatedEntity = repository.save(entity.copy(entity.getId(), dto));

          logger.info("Entity updated successfully! Entity: {}", updatedEntity);
        }
      });
    }
  }
}