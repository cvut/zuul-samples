/*
 * The MIT License
 *
 * Copyright 2013 Jakub Jirutka <jakub@jirutka.cz>.
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
import cz.cvut.zuul.support.spring.client.OAuth2RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.web.client.RestTemplate;

/**
 * This configuration is currently used only when the application is
 * initialized via {@link ServletInitializer}, otherwise is ignored.
 *
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
@Configuration
@PropertySource("classpath:/config/client-config.properties")
public class RootContextConfig {

    @Autowired Environment env;


    @Bean static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean RestTemplate oauthRestTemplate() {
        return new OAuth2RestTemplateBuilder()
                .clientCredentialsGrant()
                    .id("sample")
                    .clientId( $("oauth.client_id") )
                    .clientSecret( $("oauth.client_secret") )
                    .scope( $("oauth.scope") )
                    .accessTokenUri( $("oauth.token_endpoint") )
                    .clientAuthenticationScheme(AuthenticationScheme.form)
                .build();
    }

    @Bean RemoteService remoteService() {
        return new RemoteServiceImpl(oauthRestTemplate());
    }

    private String $(String key) {
        return env.getProperty(key);
    }
}
