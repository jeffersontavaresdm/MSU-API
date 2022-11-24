package msu_api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import msu_api.entity.MicrosoftSecurityUpdate;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class MicrosoftSecurityUpdateDTO {
  private String id;
  private String alias;
  private String documentTitle;
  private String severity;
  private OffsetDateTime initialReleaseDate;
  private OffsetDateTime currentReleaseDate;
  private String cvrfUrl;

  public MicrosoftSecurityUpdate toEntity() {
    return new MicrosoftSecurityUpdate(
      id,
      alias,
      documentTitle,
      severity,
      initialReleaseDate,
      currentReleaseDate,
      cvrfUrl
    );
  }
}