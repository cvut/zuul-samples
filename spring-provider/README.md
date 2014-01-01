Spring OAuth 2.0 Resource Provider
==================================

This project is a simple web application with RESTful API that acts as a standalone OAuth 2.0 _resource provider_. Tokens are checked against remote Zuul Authorization Server via its Check-Token Endpoint. This means that the application acts as a OAuth client as well.

It’s implemented on top of well-known [Spring Framework][spring-framework] and uses [Spring Security OAuth2][spring-security-oauth] for the OAuth part.


Configuration
-------------

You can choose between plain old XML configuration (_web.xml_ and Spring’s XMLs) or pure Java configuration ([WebApplicationInitializer][] and Spring’s [@Configuration][Configuration]) – both of them are contained, but the first one is used by default (and latter ignored). If you want to run Java-based configuration instead, just remove `src/webapp/WEB-INF/web.xml` file or build project with the profile _javaconfig_ (`mvn install -Pjavaconfig`).

### XML configs

XML configs are located in [WEB-INF][] as usual.

### Java configs

Java configs are located in package [cz.cvut.zuul.samples.provider.config][pkg-config].

### Properties

Both XML and Java configs reads some configuration variables from a properties file [/src/main/resources/config/provider-config.properties][provider-config]. There are user-specific variables that are often changed.

* **oaas.check_token_endpoint** – URL of the Check-Token Endpoint on the Zuul Authorization Server used for validation of incoming access tokens.
* **oaas.client_id** – Client identifier that is registered on the authorization server.
* **oaas.client_secret** – Secret key.
* **oaas.scope** – Scope required by the Check-Token Endpoint. Zuul OAAS uses `urn:zuul:oauth:oaas:check-token`.
* **oaas.token_endpoint** – URL of the Token Endpoint on the authorization server to obtain an access token.
* **oaas.resource_id** – Identifier of this resource provider.


[spring-framework]: http://www.springsource.org/spring-framework
[spring-security-oauth]: http://www.springsource.org/spring-security-oauth
[WebApplicationInitializer]: http://docs.spring.io/spring/docs/3.2.x/javadoc-api/org/springframework/web/WebApplicationInitializer.html
[Configuration]: http://docs.spring.io/spring/docs/3.2.x/javadoc-api/org/springframework/context/annotation/Configuration.html
[WEB-INF]: src/main/webapp/WEB-INF
[pkg-config]: src/main/java/cz/cvut/zuul/samples/provider/config
[provider-config]: src/main/resources/config/provider-config.properties
