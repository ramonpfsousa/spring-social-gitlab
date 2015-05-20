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
package org.springframework.social.gitlab.api.issue.impl;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import org.springframework.social.gitlab.api.core.PagedList;
import org.springframework.social.gitlab.api.issue.Issue;
import org.springframework.social.gitlab.api.issue.ListIssueParametersBuilder;

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
public class IssueTest extends AbstractGitlabApiTest {

    @Test
    public void testGetIssuesCreatedByCurrentUser() {
        String url = uriBuilder.api()
                .pathSegment("issues")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("issue-list"));

        List<Issue> issues = gitlab.issueOperations().getIssuesCreatedByCurrentUser().getContent();

        assertThat(issues, hasSize(2));
    }

    @Test
    public void testGetProjectIssues() {
        long projectId = 8L;

        String url = uriBuilder.api()
                .pathSegment("projects", "{projectId}", "issues")
                .buildAndExpand(projectId)
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("issue-list"));

        List<Issue> issues = gitlab.issueOperations().getProjectIssues(projectId).getContent();

        assertThat(issues, hasSize(2));
        assertThat(issues.get(0), instanceOf(Issue.class));
    }

    @Test
    public void testIssueMapping() {
        long projectId = 8L;
        long issueId = 42L;

        String url = uriBuilder.api()
                .pathSegment("projects", "{projectId}", "issues", "{issueId}")
                .buildAndExpand(projectId, issueId)
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("issue"));

        Issue issue = gitlab.issueOperations().getProjectIssue(projectId, issueId);

        assertThat(issue, is(notNullValue()));
        assertThat(issue.getId(), is(42L));
        assertThat(issue.getIid(), is(3L));
        assertThat(issue.getProjectId(), is(8L));
        assertThat(issue.getTitle(), is("Add user settings"));
        assertThat(issue.getDescription(), is("Add user settings description"));
        assertThat(issue.getState(), is("opened"));

        verifyUtcDate(issue.getUpdatedAt(), 2012, 7, 12, 13, 43, 19);
        verifyUtcDate(issue.getCreatedAt(), 2012, 6, 28, 12, 58, 6);

        assertThat(issue.getLabels(), is(notNullValue()));
        assertThat(issue.getLabels(), hasItem("feature"));

        assertThat(issue.getMilestone(), is(notNullValue()));
        assertThat(issue.getAuthor(), is(notNullValue()));
        assertThat(issue.getAssignee(), is(notNullValue()));
    }

    @Test
    public void testNestedUserMatching() {
        long projectId = 8L;
        long issueId = 42L;

        String url = uriBuilder.api()
                .pathSegment("projects", "{projectId}", "issues", "{issueId}")
                .buildAndExpand(projectId, issueId)
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("issue"));

        Issue issue = gitlab.issueOperations().getProjectIssue(projectId, issueId);
        Issue.User assignee = issue.getAssignee();
        
        assertThat(assignee.getId(), is(2L));
        assertThat(assignee.getUsername(), is("jack_smith"));
        assertThat(assignee.getEmail(), is("jack@example.com"));
        assertThat(assignee.getName(), is("Jack Smith"));
        assertThat(assignee.getState(), is("active"));
        
        verifyUtcDate(assignee.getCreatedAt(), 2012, 5, 23, 8, 1, 1);
        
    }
    
    @Test
    public void testNestedMilestoneMatching() {
        long projectId = 8L;
        long issueId = 42L;

        String url = uriBuilder.api()
                .pathSegment("projects", "{projectId}", "issues", "{issueId}")
                .buildAndExpand(projectId, issueId)
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("issue"));

        Issue.Milestone milestone = gitlab.issueOperations().getProjectIssue(projectId, issueId).getMilestone();
        
         assertThat(milestone.getId(), is(1L));
        assertThat(milestone.getTitle(), is("v1.0"));
        assertThat(milestone.getDescription(), is("v1.0 description"));
        assertThat(milestone.getState(), is("closed"));
        
        verifyUtcDate(milestone.getDueDate(), 2012, 7, 20);
        verifyUtcDate(milestone.getUpdatedAt(), 2012, 7, 4, 13, 42, 48);
        verifyUtcDate(milestone.getCreatedAt(), 2012, 6, 4, 13, 42, 48);
    }
    

    @Test
    public void testGetProjectIssuesWithParams() {

        ListIssueParametersBuilder paramsBuilder = new ListIssueParametersBuilder()
                .withLabels("foo")
                .withStateClosed()
                .withOrderByCreateAt()
                .withSortAsc();
        
        String url = uriBuilder.api()
                .pathSegment("issues")
                .queryParams(paramsBuilder.build())
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("issue-list"));
        
        
        
        PagedList<Issue> page = gitlab.issueOperations().getIssuesCreatedByCurrentUser(paramsBuilder);
        
        assertThat(page.getContent(), hasSize(2));
        assertThat(page.getContent().get(0), instanceOf(Issue.class));
        
        
    }
}
