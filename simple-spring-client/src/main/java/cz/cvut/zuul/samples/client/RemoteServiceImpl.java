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
package cz.cvut.zuul.samples.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class RemoteServiceImpl implements RemoteService {

    public static final Logger LOG = LoggerFactory.getLogger(RemoteServiceImpl.class);

	private RestTemplate rest;

    // will be set from properties file
    @Value("${resource.base_uri}") URI baseUri;
    @Value("${resource.quote_uri}") String quoteUri;


    public RemoteServiceImpl(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }


    public Quote getQuote(int id) {
        URI url = buildURI(quoteUri, id);

        LOG.info("Requesting data from: {}", url);
        return rest.getForObject(url, Quote.class);
    }


    private URI buildURI(String path, Object... uriVariables) {
        return UriComponentsBuilder.fromUri(baseUri)
                .path(path).buildAndExpand(uriVariables)
                .toUri();
    }
}
