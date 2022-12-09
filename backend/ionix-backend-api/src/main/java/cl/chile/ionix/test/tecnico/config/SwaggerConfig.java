package cl.chile.ionix.test.tecnico.config; 

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/** The Constant DEFAULT_CONTACT. */
	public static final Contact DEFAULT_CONTACT = new Contact(
            "IONIX",
            "https://www.ionix.cl",
            "jrodrigueznovoa@gmail.com");

    /** The Constant DEFAULT_API_INFO. */
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Ionix Backend Api Documentation",
            "Ionix Backend Documentation",
            "1.0",
            "PREMIUM",
            DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<VendorExtension>());
    
    /**
     * Api.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO);
    }
}