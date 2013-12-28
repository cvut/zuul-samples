Simple OAuth 2.0 Client
=======================

This project is a very simple web application that acts as an OAuth 2.0 _client_ for the _simple-provider_. It uses authorization grant [Client Credentials](http://tools.ietf.org/html/rfc6749#section-1.3.4) so the client is acting on its own behalf, i.e. without _resource owner’s_ (user) authorization.

It’s implemented on top of well-known [Spring Framework](http://www.springsource.org/spring-framework) and uses [Spring Security OAuth2](http://www.springsource.org/spring-security-oauth) for the OAuth part.


Configuration
-------------

You can choose between plain old XML configuration (_web.xml_ and Spring’s XMLs) and pure Java configuration ([WebApplicationInitializer](http://docs.spring.io/spring/docs/3.2.x/javadoc-api/org/springframework/web/WebApplicationInitializer.html) and Spring’s [@Configuration](http://docs.spring.io/spring/docs/3.2.x/javadoc-api/org/springframework/context/annotation/Configuration.html)) – both of them are contained, but the first one is used by default (and latter ignored). If you want to run Java-based configuration instead, just remove `src/webapp/WEB-INF/web.xml` file or build project with the profile _javaconfig_ (`mvn install -Pjavaconfig`).

### XML configs

XML configs are located under `src/main/webapp` as usual.

### Java configs

Java configs are located in package `cz.cvut.zuul.samples.client.config`.

### Properties

Both XML and Java configs reads some configuration variables from a properties file `/src/main/resources/config/client-config.properties`. There are user-specific variables that are often changed.

* **resource.base_uri** – Base URI of the Simple Provider API.
* **resource.quote_uri** – URI of the quote resource, relative to Base URI.

* **oauth.client_id** – Identifier of the client application that is registered on the Authorization Server.
* **oauth.client_secret** – Secret key of the client application.
* **oauth.scope** – Scope of the authorized privileges.
* **oauth.token_endpoint** – URL of the Token Endpoint on the Authorization Server to obtain an Access Token.
