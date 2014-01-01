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

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Servlet initializer that registers a {@code ContextLoaderListener} with the
 * Spring's root application context, {@code DispatcherServlet} with the API
 * context and a Spring Security filter. It is basically a replacement for
 * {@code web.xml} file (works in Servlet 3.0+ environment only).
 *
 * <p>This project contains both pure Java configuration and plain old XML
 * configuration. However, it cannot be used together so programmatic
 * initialization is disabled in the {@code web.xml} (see
 * {@code absolute-ordering} element) and XML configs are used by default.
 * If you want to use Java-based configuration, simply delete the
 * {@code web.xml} file.</p>
 */
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class[] getRootConfigClasses() {
        return new Class[]{ RootContextConfig.class };
    }

    protected String getServletName() {
        return "web";
    }

    protected Class[] getServletConfigClasses() {
        return new Class[]{ WebContextConfig.class };
    }

    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
}
