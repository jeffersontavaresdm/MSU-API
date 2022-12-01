package msu_api.exception.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@ToString
public class errorResponse implements BaseExceptionDTO {

  @Getter
  private final String message;

  @Getter
  private final Set<String> details;

  public errorResponse(String message, Set<String> details) {
    this.message = message;
    this.details = details;
  }

  public errorResponse(String message, String details) {
    this.message = message;
    this.details = Set.of(details);
  }
}