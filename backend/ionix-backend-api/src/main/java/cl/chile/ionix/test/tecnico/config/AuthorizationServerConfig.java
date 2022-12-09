package cl.chile.ionix.test.tecnico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * The Class AuthorizationServerConfig.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
    /** The client id. */
    @Value("${security.jwt.client-id}")
    private String clientId;

    /** The client secret. */
    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    /** The grant type. */
    @Value("${security.jwt.grant-type}")
    private String grantType;

    /** The scope read. */
    @Value("${security.jwt.scope-read}")
    private String scopeRead;

    /** The scope write. */
    @Value("${security.jwt.scope-write}")
    private String scopeWrite = "write";

    /** The resource ids. */
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    // Beans

    /** The token store. */
    @Autowired
    private TokenStore tokenStore;

    /** The access token converter. */
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    /** The authentication manager. */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** The bcrypt. */
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    /**
     * Configure.
     *
     * @param configurer the configurer
     * @throws Exception the exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory().withClient(clientId).secret(bcrypt.encode(clientSecret)).authorizedGrantTypes(grantType, "refresh_token")
                .scopes(scopeRead, scopeWrite).resourceIds(resourceIds).accessTokenValiditySeconds(900)
                .refreshTokenValiditySeconds(0);
    }

    /**
     * Configure.
     *
     * @param endpoints the endpoints
     * @throws Exception the exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter).tokenEnhancer(enhancerChain).authenticationManager(authenticationManager);
    }
}
