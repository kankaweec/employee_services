package co.th.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class SpringBootSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwaggerApplication.class, args);
	}

	@Bean
	public Docket swaggerConfigs() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("co.th.employee"))
				.build()
				.apiInfo(createApiInfo());
	}

	public ApiInfo createApiInfo(){

		/*ApiInfo(java.lang.String title,
				java.lang.String description,
				java.lang.String version,
				java.lang.String termsOfServiceUrl,
				springfox.documentation.service.Contact contact,
				java.lang.String license,
				java.lang.String licenseUrl,
				java.util.Collection<springfox.documentation.service.VendorExtension> vendorExtensions)*/
		return new ApiInfo("Employee information api",
				"Simple REST API for employees",
				 "1.0",
				"Free to use",
				new Contact("Kankawee Chanthorn", null,"kankawee.cht@gmail.com"),
				"API license",
				null,
				Collections.emptyList());
	}





}
