package skypro_bot.skybot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition
public class SkybotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkybotApplication.class, args);
	}

}
