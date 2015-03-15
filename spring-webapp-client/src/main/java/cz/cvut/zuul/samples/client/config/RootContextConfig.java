/*
 * The MIT License
 *
 * Copyright 2013-2014 Czech Technical University in Prague.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cz.cvut.zuul.samples.client.config;

import cz.cvut.zuul.samples.client.RemoteService;
import cz.cvut.zuul.samples.client.RemoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

import static java.util.Collections.singletonList;

/**
 * This configuration is currently used only when the application is
 * initialized via {@link ServletInitializer}, otherwise is ignored.
 */
@Configuration
@EnableOAuth2Client
@PropertySource("classpath:/config/client-config.properties")
public class RootContextConfig {

    @Autowired Environment env;
    @Autowired OAuth2ClientContext oauth2Context;


    @Bean static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean RestTemplate oauthRestTemplate() {
        AuthorizationCodeResourceDetails r = new AuthorizationCodeResourceDetails();
        r.setId("sample");
        r.setClientId( p("oauth.client_id") );
        r.setClientSecret( p("oauth.client_secret") );
        r.setScope(singletonList( p("oauth.scope") ));
        r.setAccessTokenUri( p("oauth.token_endpoint") );

        return new OAuth2RestTemplate(r, oauth2Context);
    }

    @Bean RemoteService remoteService() {
        return new RemoteServiceImpl(oauthRestTemplate());
    }


    private String p(String key) {
        return env.getProperty(key);
    }
}
