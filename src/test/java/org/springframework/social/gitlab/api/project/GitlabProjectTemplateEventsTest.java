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
package org.springframework.social.gitlab.api.project;

import java.util.List;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import static org.springframework.social.gitlab.api.utils.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProjectTemplateEventsTest extends AbstractGitlabApiTest {

    @Test
    public void testGetProjectEvents() {
        String url = uriBuilder.builder().pathSegment("projects", "3", "events").toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-event-list"), MediaType.APPLICATION_JSON));

        List<ProjectEvent> events = gitlab.projectOperations().getProjectEvents(3);

        assertThat(events, is(notNullValue()));
        assertThat(events, hasSize(3));
    }

    @Test
    public void testProjectEventMapping() {
        String url = uriBuilder.builder().pathSegment("projects", "3", "events").toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-event-list"), MediaType.APPLICATION_JSON));

        ProjectEvent event = gitlab.projectOperations().getProjectEvents(3).get(0);

        assertThat(event, is(notNullValue()));
        assertThat(event.getTitle(), is("event-title-15"));
        assertThat(event.getProjectId(), is(15L));
        assertThat(event.getActionName(), is("closed"));
        assertThat(event.getTargetId(), is(830L));
        assertThat(event.getTargetType(), is("Issue"));
        assertThat(event.getAuthorId(), is(1L));
        assertThat(event.getAuthorUsername(), is("john"));
        assertThat(event.getTargetTitle(), is("Public project search field"));
        assertThat(event.getData(), is(nullValue()));
    }

    
    @Test
    public void testProjectEventWithDataMapping() {
        String url = uriBuilder.builder().pathSegment("projects", "3", "events").toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-event-list"), MediaType.APPLICATION_JSON));

        ProjectEvent event = gitlab.projectOperations().getProjectEvents(3).get(1);

        assertThat(event.getData(), is(notNullValue()));
        assertThat(event.getData(), hasKey("before"));
        
    }
}
