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
package org.springframework.social.gitlab.api.core.impl;

import java.net.URI;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.springframework.http.HttpMethod;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import org.springframework.social.gitlab.api.core.PagedList;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 *
 * @author p.hoeffling
 */
public class GitlabTemplateTest extends AbstractGitlabApiTest {

    @Test
    public void testProfileOperations() {
        assertThat(gitlab.profileOperations(), is(notNullValue()));
    }

    @Test
    public void testUserOperations() {
        assertThat(gitlab.userOperations(), is(notNullValue()));
    }

    @Test
    public void testProjectOperations() {
        assertThat(gitlab.projectOperations(), is(notNullValue()));
    }

    @Test
    public void testRestOperations() {
        assertThat(gitlab.restOperations(), is(notNullValue()));
    }

    @Test
    public void testUriBuilder() {
        assertThat(gitlab.uriBuilder(), is(notNullValue()));
    }

    @Test
    public void testGetForPage() {
        URI uri = uriBuilder.api().pathSegment("paging-test").build().toUri();

        mockServer
                .expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withPagedJsonResourceSuccess(
                                "string-list",
                                "<http://gitlab.com/api/v3/paging-test?page=2&per_page=20>; rel=\"next\", <http://gitlab.com/api/v3/paging-test?page=1&per_page=20>; rel=\"first\", <http://gitlab.com/api/v3/paging-test?page=2&per_page=20>; rel=\"last\""
                        ));

        PagedList<String> paged = gitlab.getForPage(uri, String.class);

        assertThat(paged, is(notNullValue()));
        // Body
        assertThat(paged.getData(), is(notNullValue()));
        assertThat(paged.getData(), hasSize(20));

        // Paging
        assertThat(paged.getPaging(), is(notNullValue()));
        assertThat(paged.getPaging().hasPrevious(), is(false));
        assertThat(paged.getPaging().hasNext(), is(true));
        assertThat(paged.getPaging().getNext().getPage(), is(2));
        assertThat(paged.getPaging().getNext().getPerPage(), is(20));
    }

}
