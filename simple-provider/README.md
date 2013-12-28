Simple OAuth2 resource provider
===============================

This project is a simple web application with RESTful API that acts as a standalone OAuth 2.0 _resource provider_. Tokens are checked against remote Zuul Authorization Server via its Check-Token Endpoint. This means that the application acts as a OAuth client as well.

It’s implemented on top of well-known [Spring Framework](http://www.springsource.org/spring-framework) and uses [Spring Security OAuth2](http://www.springsource.org/spring-security-oauth) for the OAuth part.


Settings
--------

Configuration variables are located in `/src/main/resources/config/provider-config.properties`. These values are included in `WEB-INF/spring/security.xml` where is the Spring security configured.

* **oaas.check_token_endpoint** – URL of the Check-Token Endpoint on the Zuul Authorization Server used for validation of incoming Access Tokens.
* **oaas.client_id** – Client identifier that is registered on the Authorization Server.
* **oaas.client_secret** – Secret key.
* **oaas.scope** – Scope required by the Check-Token Endpoint. Zuul OAAS uses `urn:ctu:oauth:oaas:check-token`.
* **oaas.token_endpoint** – URL of the Token Endpoint on the Authorization Server to obtain an Access Token.
* **oaas.resource_id** – Identifier of this resource provider.
