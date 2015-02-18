/*
 * Copyright 2015 p.hoeffling.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.gitlab.api.core;

import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author p.hoeffling
 */
public class LinkHeaderParserTest {

    private static final String SINGLE = "<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"first\", <http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"last\"";
    private static final String FIRST = "<http://gitlab.com/api/v3/users?page=2&per_page=10>; rel=\"next\", <http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"first\", <http://gitlab.com/api/v3/users?page=2&per_page=10>; rel=\"last\"";
    private static final String LAST = "<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"prev\", <http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"first\", <http://gitlab.com/api/v3/users?page=2&per_page=10>; rel=\"last\"";
    private static final String MIDDLE = "<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"prev\", <http://gitlab.com/api/v3/users?page=3&per_page=10>; rel=\"next\", <http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"first\", <http://gitlab.com/api/v3/users?page=3&per_page=10>; rel=\"last\"";

    private LinkHeaderParser parser;

    @Before
    public void setUp() {
        parser = new LinkHeaderParser();
    }

    @Test
    public void testNoHeader() {

        Paging paging = parser.buildPaging(null);

        assertThat(paging, is(notNullValue()));
        assertThat(paging.hasNext(), is(false));
        assertThat(paging.hasPrevious(), is(false));
    }

    @Test
    public void testSinglePage() {

        Paging paging = parser.buildPaging(SINGLE);

        assertThat(paging, is(notNullValue()));
        assertThat(paging.hasNext(), is(false));
        assertThat(paging.hasPrevious(), is(false));
    }

    @Test
    public void testFirstPage() {

        Paging paging = parser.buildPaging(FIRST);

        assertThat(paging, is(notNullValue()));
        assertThat(paging.hasNext(), is(true));
        assertThat(paging.hasPrevious(), is(false));

        assertThat(paging.getNext().getPage(), is(2));
        assertThat(paging.getNext().getPerPage(), is(10));

    }

    @Test
    public void testLastPage() {

        Paging paging = parser.buildPaging(LAST);

        assertThat(paging, is(notNullValue()));
        assertThat(paging.hasNext(), is(false));
        assertThat(paging.hasPrevious(), is(true));

        assertThat(paging.getPrevious().getPage(), is(1));
        assertThat(paging.getPrevious().getPerPage(), is(10));

    }

    @Test
    public void testMiddlePage() {

        Paging paging = parser.buildPaging(MIDDLE);

        assertThat(paging, is(notNullValue()));
        assertThat(paging.hasNext(), is(true));
        assertThat(paging.hasPrevious(), is(true));

        assertThat(paging.getNext().getPage(), is(3));
        assertThat(paging.getNext().getPerPage(), is(10));

        assertThat(paging.getPrevious().getPage(), is(1));
        assertThat(paging.getPrevious().getPerPage(), is(10));

    }

    @Test
    public void testNoHeaderExtractLinkRelations() {

        Map<String, String> map = parser.extractLinkRelations(null);

        assertThat(map, is(notNullValue()));
        assertThat(map.entrySet(), is(empty()));
    }

    @Test
    public void testSinglePageExtractLinkRelations() {

        Map<String, String> map = parser.extractLinkRelations(SINGLE);

        assertThat(map, is(notNullValue()));
        assertThat(map.entrySet(), hasSize(2));
        assertThat(map, hasEntry("first", "http://gitlab.com/api/v3/users?page=1&per_page=10"));
        assertThat(map, hasEntry("last", "http://gitlab.com/api/v3/users?page=1&per_page=10"));
    }

    @Test
    public void testFirstPageExtractLinkRelations() {

        Map<String, String> map = parser.extractLinkRelations(FIRST);

        assertThat(map, is(notNullValue()));
        assertThat(map.entrySet(), hasSize(3));
        assertThat(map, hasEntry("first", "http://gitlab.com/api/v3/users?page=1&per_page=10"));
        assertThat(map, hasEntry("last", "http://gitlab.com/api/v3/users?page=2&per_page=10"));
        assertThat(map, hasEntry("next", "http://gitlab.com/api/v3/users?page=2&per_page=10"));
    }

    @Test
    public void testLastPageExtractLinkRelations() {

        Map<String, String> map = parser.extractLinkRelations(LAST);

        assertThat(map, is(notNullValue()));
        assertThat(map.entrySet(), hasSize(3));
        assertThat(map, hasEntry("first", "http://gitlab.com/api/v3/users?page=1&per_page=10"));
        assertThat(map, hasEntry("last", "http://gitlab.com/api/v3/users?page=2&per_page=10"));
        assertThat(map, hasEntry("prev", "http://gitlab.com/api/v3/users?page=1&per_page=10"));
    }

    @Test
    public void testMiddlePageExtractLinkRelations() {

        Map<String, String> map = parser.extractLinkRelations(MIDDLE);

        assertThat(map, is(notNullValue()));
        assertThat(map.entrySet(), hasSize(4));
        assertThat(map, hasEntry("first", "http://gitlab.com/api/v3/users?page=1&per_page=10"));
        assertThat(map, hasEntry("last", "http://gitlab.com/api/v3/users?page=3&per_page=10"));
        assertThat(map, hasEntry("prev", "http://gitlab.com/api/v3/users?page=1&per_page=10"));
        assertThat(map, hasEntry("next", "http://gitlab.com/api/v3/users?page=3&per_page=10"));
    }

    @Test
    public void testExtractRelation() {

        assertThat(parser.extractRelation(null), is(nullValue()));
        assertThat(parser.extractRelation("<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"first\""), is("first"));
        assertThat(parser.extractRelation("<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"last\""), is("last"));
        assertThat(parser.extractRelation("<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"prev\""), is("prev"));
        assertThat(parser.extractRelation("<http://gitlab.com/api/v3/users?page=2&per_page=10>; rel=\"next\""), is("next"));

    }

    @Test
    public void testExtractLink() {

        assertThat(parser.extractLink(null), is(nullValue()));
        assertThat(parser.extractLink("<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"first\""), is("http://gitlab.com/api/v3/users?page=1&per_page=10"));
        assertThat(parser.extractLink("<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"last\""), is("http://gitlab.com/api/v3/users?page=1&per_page=10"));
        assertThat(parser.extractLink("<http://gitlab.com/api/v3/users?page=1&per_page=10>; rel=\"prev\""), is("http://gitlab.com/api/v3/users?page=1&per_page=10"));
        assertThat(parser.extractLink("<http://gitlab.com/api/v3/users?page=2&per_page=10>; rel=\"next\""), is("http://gitlab.com/api/v3/users?page=2&per_page=10"));

    }

    @Test
    public void testBuildPagingParameters() {

        assertThat(parser.buildPagingParameters(null), is(nullValue()));
        assertThat(parser.buildPagingParameters("http://gitlab.com/api/v3/users?page=1&per_page=10"), is(not(nullValue())));
        assertThat(parser.buildPagingParameters("http://gitlab.com/api/v3/users?page=1&per_page=10").getPage(), is(1));
        assertThat(parser.buildPagingParameters("http://gitlab.com/api/v3/users?page=1&per_page=10").getPerPage(), is(10));

        assertThat(parser.buildPagingParameters("http://gitlab.com/api/v3/users"), is(not(nullValue())));
        assertThat(parser.buildPagingParameters("http://gitlab.com/api/v3/users").getPage(), is(0));
        assertThat(parser.buildPagingParameters("http://gitlab.com/api/v3/users").getPerPage(), is(0));
    }
}
