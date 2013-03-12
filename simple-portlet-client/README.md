Simple Portlet OAuth2 Client
============================

This project is a very simple Liferay portlet that acts as an OAuth 2.0 _client_ for the _simple-provider_. It uses authorization grant [Client Credentials](http://tools.ietf.org/html/rfc6749#section-1.3.4) so the client is acting on its own behalf, i.e. without _resource owner’s_ (user) authorization.

It’s implemented on top of well-known [Spring Framework](http://www.springsource.org/spring-framework) and uses [Spring Security OAuth2](http://www.springsource.org/spring-security-oauth) for the OAuth part.


Settings
--------

Configuration variables are located in `oauth-client.properties` and `resource-server.properties` files under `/src/main/webapp/WEB-INF`. These values are included in `WEB-INF/spring/root.xml` where is the Spring root context configured.

**oauth-client.properties:**

* **oauth.client_id** – Identifier of the client application that is registered on the Authorization Server.
* **oauth.client_secret** – Secret key of the client application.
* **oauth.scope** – Scope of the authorized privileges.
* **oauth.token_endpoint** – URL of the token endpoint on the Authorization Server to obtain an Access Token.

**resource-server.properties:**

* **resource.base_uri** – Base URI of the Simple Provider API.
* **resource.quote_uri** – URI of the quote resource, relative to Base URI.
