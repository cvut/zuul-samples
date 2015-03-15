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
package cz.cvut.zuul.samples.provider.config;

import cz.cvut.zuul.samples.provider.FileQuotesDao;
import cz.cvut.zuul.samples.provider.QuotesDao;
import cz.cvut.zuul.samples.provider.config.RootContextConfig.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import java.io.IOException;

/**
 * This configuration is currently used only when the application is
 * initialized via {@link ServletInitializer}, otherwise is ignored.
 */
@Configuration
@Import(SecurityConfig.class)
@PropertySource("classpath:/config/provider-config.properties")
public class RootContextConfig {

    @Bean QuotesDao quotesDao() throws IOException {
        return new FileQuotesDao(new ClassPathResource("quotes.txt"));
    }


    @Configuration
    @EnableWebSecurity
    @EnableResourceServer
    public static class SecurityConfig implements ResourceServerConfigurer {

        @Autowired Environment env;

        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources
                .tokenServices( remoteTokenServices() )
                .resourceId( p("oaas.resource_id") );
        }

        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/api/**")
                        .access("#oauth2.hasScope('urn:zuul:oauth:quotes.read')");
        }

        @Bean RemoteTokenServices remoteTokenServices() {
            RemoteTokenServices s = new RemoteTokenServices();
            s.setCheckTokenEndpointUrl( p("oaas.endpoint.check_token") );
            s.setClientId( p("oaas.client_id") );
            s.setClientSecret( p("oaas.client_secret") );
            return s;
        }

        private String p(String key) {
            return env.getProperty(key);
        }
    }
}
