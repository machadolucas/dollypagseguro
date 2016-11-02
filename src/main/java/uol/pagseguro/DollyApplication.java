package uol.pagseguro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
public class DollyApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DollyApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("dolly").apiInfo(apiInfo()).select().paths(regex
				("/*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Dolly").description("O melhor sistema, amiguinho!").version("0.1").build();
	}
}
