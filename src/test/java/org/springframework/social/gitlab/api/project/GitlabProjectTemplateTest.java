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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
public class GitlabProjectTemplateTest extends AbstractGitlabApiTest {

    @Test
    public void testGetProjectsAccesibleByCurrentUser() {
        String url = uriBuilder.builder()
                .pathSegment("projects")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-list"), MediaType.APPLICATION_JSON));

        List<Project> projects = gitlab.projectOperations().getProjectsAccessibleByCurrentUser();

        assertThat(projects, hasSize(2));
    }

    @Test
    public void testGetProjectsAccesibleByCurrentUserWithParams() {
        String url = uriBuilder.builder()
                .pathSegment("projects")
                .queryParam("archived", true)
                .queryParam("order_by", "name")
                .queryParam("sort", "desc")
                .queryParam("search", "foo")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-list"), MediaType.APPLICATION_JSON));

        ListProjectParametersBuilder params = new ListProjectParametersBuilder()
                .withArchived()
                .withOrderBy(ListProjectParametersBuilder.OrderBy.Name)
                .withSort(ListProjectParametersBuilder.Sort.Desc)
                .withSearch("foo");

        List<Project> projects = gitlab.projectOperations().getProjectsAccessibleByCurrentUser(params);

        assertThat(projects, hasSize(2));
    }

    @Test
    public void testGetProjectsOwnedByCurrentUser() {
        String url = uriBuilder.builder()
                .pathSegment("projects", "owned")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-list"), MediaType.APPLICATION_JSON));

        List<Project> projects = gitlab.projectOperations().getProjectsOwnedByCurrentUser();

        assertThat(projects, hasSize(2));
    }

    @Test
    public void testGetProjectsOwnedByCurrentUserWithParams() {
        String url = uriBuilder.builder()
                .pathSegment("projects", "owned")
                .queryParam("archived", true)
                .queryParam("order_by", "name")
                .queryParam("sort", "desc")
                .queryParam("search", "foo")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-list"), MediaType.APPLICATION_JSON));

        ListProjectParametersBuilder params = new ListProjectParametersBuilder()
                .withArchived()
                .withOrderBy(ListProjectParametersBuilder.OrderBy.Name)
                .withSort(ListProjectParametersBuilder.Sort.Desc)
                .withSearch("foo");

        List<Project> projects = gitlab.projectOperations().getProjectsOwnedByCurrentUser(params);

        assertThat(projects, hasSize(2));
    }

    @Test
    public void testProjectMapping() {
        String url = uriBuilder.builder().pathSegment("projects", "3").toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project"), MediaType.APPLICATION_JSON));

        Project project = gitlab.projectOperations().getProject(3);

        assertThat(project, is(notNullValue()));
        assertThat(project.getId(), is(3L));
        assertThat(project.getDescription(), is("sample description"));
        assertThat(project.getDefaultBranch(), is("master"));
        assertThat(project.isPublic(), is(false));
        assertThat(project.getVisibilityLevel(), is(10L));
        assertThat(project.getSshUrlToRepo(), is("git@example.com:diaspora/diaspora-project-site.git"));
        assertThat(project.getHttpUrlToRepo(), is("http://example.com/diaspora/diaspora-project-site.git"));
        assertThat(project.getWebUrl(), is("http://example.com/diaspora/diaspora-project-site"));
        assertThat(project.getName(), is("Diaspora Project Site"));
        assertThat(project.getNameWithNamespace(), is("Diaspora / Diaspora Project Site"));
        assertThat(project.getPath(), is("diaspora-project-site"));
        assertThat(project.getPathWithNamespace(), is("diaspora/diaspora-project-site"));
        assertThat(project.isIssuesEnabled(), is(true));
        assertThat(project.isMergeRequestsEnabled(), is(true));
        assertThat(project.isWikiEnabled(), is(true));
        assertThat(project.isSnippetsEnabled(), is(false));
        assertThat(project.isArchived(), is(false));

        verifyUtcDate(project.getCreatedAt(), 2013, 9, 30, 13, 46, 02);
        verifyUtcDate(project.getLastActivityAt(), 2013, 9, 30, 13, 46, 02);

    }

    @Test
    public void testProjectOwnerMapping() {
        String url = uriBuilder.builder().pathSegment("projects", "3").toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project"), MediaType.APPLICATION_JSON));

        Project.Owner owner = gitlab.projectOperations().getProject(3).getOwner();

        assertThat(owner, is(notNullValue()));
        assertThat(owner.getId(), is(3L));
        assertThat(owner.getName(), is("Diaspora"));
        verifyUtcDate(owner.getCreatedAt(), 2013, 9, 30, 13, 46, 02);
    }

    @Test
    public void testProjectNamespaceMapping() {
        String url = uriBuilder.builder().pathSegment("projects", "3").toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project"), MediaType.APPLICATION_JSON));

        Project.Namespace namespace = gitlab.projectOperations().getProject(3).getNamespace();

        assertThat(namespace, is(notNullValue()));
        assertThat(namespace.getId(), is(3L));
        assertThat(namespace.getName(), is("Diaspora"));
        assertThat(namespace.getDescription(), is("Namespace description"));
        assertThat(namespace.getPath(), is("diaspora"));
        assertThat(namespace.getOwnerId(), is(1L));
        verifyUtcDate(namespace.getCreatedAt(), 2013, 9, 30, 13, 46, 02);
        verifyUtcDate(namespace.getUpdatedAt(), 2013, 9, 30, 13, 46, 02);
    }

    @Test
    public void testProjectPermissionsMapping() {
        String url = uriBuilder.builder().pathSegment("projects", "3").toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project"), MediaType.APPLICATION_JSON));

        Project.Permissions permissions = gitlab.projectOperations().getProject(3).getPermissions();

        assertThat(permissions, is(notNullValue()));

        assertThat(permissions.getProjectAccess(), is(notNullValue()));
        assertThat(permissions.getProjectAccess().getAccessLevel(), is(10L));
        assertThat(permissions.getProjectAccess().getNotificationLevel(), is(3L));

        assertThat(permissions.getGroupAccess(), is(notNullValue()));
        assertThat(permissions.getGroupAccess().getAccessLevel(), is(50L));
        assertThat(permissions.getGroupAccess().getNotificationLevel(), is(4L));
    }
}
