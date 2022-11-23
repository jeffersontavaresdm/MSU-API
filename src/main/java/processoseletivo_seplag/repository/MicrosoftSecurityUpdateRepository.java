package processoseletivo_seplag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import processoseletivo_seplag.entity.MicrosoftSecurityUpdate;

public interface MicrosoftSecurityUpdateRepository extends JpaRepository<MicrosoftSecurityUpdate, Long> {
  MicrosoftSecurityUpdate findByKey(String key);
}