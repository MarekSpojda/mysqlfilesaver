package pl.marekspojda.MySqlFileSaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MySqlFileSaverApplication {
	public static void main(String[] args) {
//		SpringApplication.run(MySqlFileSaverApplication.class, args);
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MySqlFileSaverApplication.class);

	    builder.headless(false);
	    ConfigurableApplicationContext context = builder.run(args);
	}
}