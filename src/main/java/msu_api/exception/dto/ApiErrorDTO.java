package msu_api.exception.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Set;

@ToString
public class ApiErrorDTO implements BaseExceptionDTO {

  @Getter
  private final HttpStatus status;

  @Getter
  private final Set<String> errors;

  public ApiErrorDTO(HttpStatus status, Set<String> errors) {
    this.status = status;
    this.errors = errors;
  }

  public ApiErrorDTO(HttpStatus status, String error) {
    this.status = status;
    errors = Set.of(error);
  }
}