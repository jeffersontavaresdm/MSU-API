package msu_api.exception.handler;

import msu_api.exception.base.NotFoundException;
import msu_api.exception.dto.NotFoundExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SecurityUpdateControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NotFoundException.class})
  public ResponseEntity<NotFoundExceptionDTO> handleException(NotFoundException exception) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(new NotFoundExceptionDTO(exception.getKey(), exception.getDetailedMessage()));
  }
}