package mehdi.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import mehdi.blog.BlogCollaboratifApplication;

@Configuration
@SpringBootApplication
public class BlogCollaboratifApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogCollaboratifApplication.class, args);
	}

}
