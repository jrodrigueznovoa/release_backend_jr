package cl.chile.ionix.test.tecnico.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    /** The signin key. */
    @Value("${security.signing-key}")
    private String signinKey;

    /** The encoding strength. */
    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    /** The security realm. */
    @Value("${security.security-realm}")
    private String securityRealm;


    // Beans

    /** The user details service. */
    @Autowired
    private UserDetailsService userDetailsService;

    /** The data source. */
    @Autowired
    private DataSource dataSource;

    /** The bcrypt. */
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    /**
     * Password encoder.
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * Authentication manager.
     *
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * Configure.
     *
     * @param auth the auth
     * @throws Exception the exception
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
    }

    /**
     * Configure.
     *
     * @param http the http
     * @throws Exception the exception
     */
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName(securityRealm)
                .and()
                .csrf()
                .disable();
    }

    /**
     * Access token converter.
     *
     * @return the jwt access token converter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signinKey);
        return converter;
    }

    /**
     * Token store.
     *
     * @return the token store
     */
    @Bean
    public TokenStore tokenStore() {
        // in memory
        return new JwtTokenStore(accessTokenConverter());
        // on bdd
        //return new JdbcTokenStore(this.dataSource);
    }

    /**
     * Token services.
     *
     * @return the default token services
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(false);
        return defaultTokenServices;
    }
}
