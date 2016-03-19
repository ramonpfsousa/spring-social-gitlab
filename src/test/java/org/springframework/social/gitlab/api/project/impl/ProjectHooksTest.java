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
package org.springframework.social.gitlab.api.project.impl;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import org.springframework.social.gitlab.api.project.ProjectHook;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.social.gitlab.api.utils.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 *
 * @author p.hoeffling
 */
public class ProjectHooksTest extends AbstractGitlabApiTest {

    @Test
    public void testGetProjectHooks() {
        String url = uriBuilder.api()
                .pathSegment("projects", "3", "hooks")
                .build()
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("project-hook-list"));

        List<ProjectHook> hooks = gitlab.projectOperations().getProjectHooks(3);

        assertThat(hooks, is(notNullValue()));
        assertThat(hooks, hasSize(3));
        assertThat(hooks.get(0), instanceOf(ProjectHook.class));
    }

    @Test
    public void testGetProjectHook() {
        String url = uriBuilder.api()
                .pathSegment("projects", "3", "hooks", "1")
                .build()
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("project-hook"));

        ProjectHook hook = gitlab.projectOperations().getProjectHook(3, 1);

        assertThat(hook, is(notNullValue()));

        assertThat(hook.getId(), is(1L));
        assertThat(hook.getUrl(), is("http://example.com/hook"));
        assertThat(hook.getProjectId(), is(3L));
        assertThat(hook.isPushEvents(), is(true));
        assertThat(hook.isIssuesEvents(), is(true));
        assertThat(hook.isMergeRequestsEvents(), is(true));

        verifyUtcDate(hook.getCreatedAt(), 2012, 10, 12, 17, 4, 47);
    }

}
