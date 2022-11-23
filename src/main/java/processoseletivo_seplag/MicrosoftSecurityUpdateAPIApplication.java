package processoseletivo_seplag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MicrosoftSecurityUpdateAPIApplication {

  public static void main(String[] args) {
    SpringApplication.run(MicrosoftSecurityUpdateAPIApplication.class, args);
  }
}