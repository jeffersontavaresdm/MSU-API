package msu_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import msu_api.entity.MicrosoftSecurityUpdate;

import java.util.List;

public interface MicrosoftSecurityUpdateRepository extends JpaRepository<MicrosoftSecurityUpdate, Long> {
  MicrosoftSecurityUpdate findByKey(String key);

  List<MicrosoftSecurityUpdate> findByOrderByIdAsc();
}