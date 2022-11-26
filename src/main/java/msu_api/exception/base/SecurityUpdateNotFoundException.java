package msu_api.exception.base;

public class SecurityUpdateNotFoundException extends NotFoundException {

  public SecurityUpdateNotFoundException() {}

  public SecurityUpdateNotFoundException(String key) {
    super(key);
  }
}
