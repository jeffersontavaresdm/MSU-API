package processoseletivo_seplag.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

  public static Logger loggerFor(Object clazz) {
    return LoggerFactory.getLogger(clazz.getClass());
  }
}