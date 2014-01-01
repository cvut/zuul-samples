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
package cz.cvut.zuul.samples.provider;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.bind.DatatypeConverter.parseInt;

public class FileQuotesDao implements QuotesDao {

    private List<Quote> quotes;


    public FileQuotesDao(Resource quotesFile) throws IOException {
        this.quotes = loadQuotes(quotesFile);
    }

    public Quote getQuote(int id) {
        if (id < 0 || id >= quotes.size()) {
            throw new IllegalArgumentException(String.format("Quote with id = %s does not exist", id));
        }
        return quotes.get(id);
    }


    private List<Quote> loadQuotes(Resource file) throws IOException {
        List<Quote> list = new ArrayList<>(100);

        try (InputStream stream = file.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line; while ((line = reader.readLine()) != null) {
                list.add(parseLine(line));
            }
        }
        return list;
    }

    private Quote parseLine(String line) {
        String[] cols = line.split("\t");
        Assert.isTrue(cols.length == 6, "Line must have 6 columns");

        return new Quote(cols[1], cols[2], cols[4], parseInt(cols[5]));
    }
}
