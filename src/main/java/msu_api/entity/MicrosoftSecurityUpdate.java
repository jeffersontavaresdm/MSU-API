package msu_api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import msu_api.entity.dto.MicrosoftSecurityUpdateDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
@Getter
@ToString
@AllArgsConstructor
public class MicrosoftSecurityUpdate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String key;
  private String alias;
  private String documentTitle;
  private String severity;
  private OffsetDateTime initialReleaseDate;
  private OffsetDateTime currentReleaseDate;
  private String cvrfUrl;

  public MicrosoftSecurityUpdate() {}

  public MicrosoftSecurityUpdate(
    String key,
    String alias,
    String documentTitle,
    String severity,
    OffsetDateTime initialReleaseDate,
    OffsetDateTime currentReleaseDate,
    String cvrfUrl
  ) {
    this.key = key;
    this.alias = alias;
    this.documentTitle = documentTitle;
    this.severity = severity;
    this.initialReleaseDate = initialReleaseDate;
    this.currentReleaseDate = currentReleaseDate;
    this.cvrfUrl = cvrfUrl;
  }

  public MicrosoftSecurityUpdate copy(Long entityID, MicrosoftSecurityUpdateDTO dto) {
    return new MicrosoftSecurityUpdate(
      this.id = entityID,
      this.key = dto.getAlias(),
      this.alias = dto.getAlias(),
      this.documentTitle = dto.getDocumentTitle(),
      this.severity = dto.getSeverity(),
      this.initialReleaseDate = dto.getInitialReleaseDate(),
      this.currentReleaseDate = dto.getCurrentReleaseDate(),
      this.cvrfUrl = dto.getCvrfUrl()
    );
  }
}