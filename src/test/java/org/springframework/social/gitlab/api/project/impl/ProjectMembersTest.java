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
import org.springframework.social.gitlab.api.project.ProjectMember;

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
public class ProjectMembersTest extends AbstractGitlabApiTest {

    @Test
    public void testGetProjectMembers() {
        String url = uriBuilder.api()
                .pathSegment("projects", "3", "members")
                .build()
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("project-member-list"));

        List<ProjectMember> members = gitlab.projectOperations().getProjectMembers(3);

        assertThat(members, is(notNullValue()));
        assertThat(members, hasSize(3));
        assertThat(members.get(0), instanceOf(ProjectMember.class));
    }

    @Test
    public void testProjectMemberMapping() {
        String url = uriBuilder.api()
                .pathSegment("projects", "3", "members", "1")
                .build()
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("project-member"));

        ProjectMember member = gitlab.projectOperations().getProjectMember(3, 1);

        assertThat(member, is(notNullValue()));

        assertThat(member.getId(), is(1L));
        assertThat(member.getUsername(), is("john_smith"));
        assertThat(member.getEmail(), is("john@example.com"));
        assertThat(member.getName(), is("John Smith"));
        assertThat(member.getState(), is("active"));
        assertThat(member.getAccessLevel(), is(40L));

        verifyUtcDate(member.getCreatedAt(), 2012, 5, 23, 8, 0, 58);

    }

}
