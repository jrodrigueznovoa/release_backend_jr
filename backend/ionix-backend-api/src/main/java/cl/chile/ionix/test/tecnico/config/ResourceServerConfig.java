package cl.chile.ionix.test.tecnico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Class ResourceServerConfig.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/** The token services. */
	@Autowired
	private ResourceServerTokenServices tokenServices;

	/** The resource ids. */
	@Value("${security.jwt.resource-ids}")
	private String resourceIds;

	/**
	 * Configure.
	 *
	 * @param resources the resources
	 * @throws Exception the exception
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceIds).tokenServices(tokenServices);
	}

	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
					AuthenticationException e) throws IOException, ServletException {
				httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			}
		})
		.and()
        .requestMatchers()
        .and()
        .authorizeRequests()
        .antMatchers("/swagger.ui.html/**").permitAll()
        .antMatchers("/search/**").permitAll()
        .antMatchers("/api/search/**").permitAll()
        .antMatchers("/api/user/search/**").permitAll()
        .antMatchers("/api/user/new").authenticated()
        .antMatchers("/api/user/delete/**").authenticated();
  
	}
}
