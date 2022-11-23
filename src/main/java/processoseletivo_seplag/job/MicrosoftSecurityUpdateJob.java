package processoseletivo_seplag.job;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import processoseletivo_seplag.api.MicrosoftSecurityUpdateAPI;
import processoseletivo_seplag.entity.MicrosoftSecurityUpdate;
import processoseletivo_seplag.repository.MicrosoftSecurityUpdateRepository;
import processoseletivo_seplag.utils.LoggerUtils;

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