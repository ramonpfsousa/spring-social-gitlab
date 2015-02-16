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

import org.springframework.social.gitlab.api.AbstractGitlabApiTest;

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
public class ProjectBranchesTest extends AbstractGitlabApiTest {

    @Test
    public void testGetProjectBranches() {
        String url = uriBuilder.api()
                .pathSegment("projects", "3", "repository", "branches")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("project-branch-list"), MediaType.APPLICATION_JSON));

        List<ProjectBranch> branches = gitlab.projectOperations().getProjectBranches(3);

        assertThat(branches, is(notNullValue()));
        assertThat(branches, hasSize(2));

    }

    @Test
    public void testGetProjectBranch() {
        String url = uriBuilder.api()
                .pathSegment("projects", "3", "repository", "branches", "async")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("project-branch"), MediaType.APPLICATION_JSON));

        ProjectBranch branch = gitlab.projectOperations().getProjectBranch(3, "async");

        assertThat(branch, is(notNullValue()));
        assertThat(branch.getName(), is("async"));
        assertThat(branch.isProtected(), is(true));

        // Nested: commit
        ProjectBranch.Commit commit = branch.getCommit();
        assertThat(commit, is(notNullValue()));
        assertThat(commit.getId(), is("a2b702edecdf41f07b42653eb1abe30ce98b9fca"));
        assertThat(commit.getTree(), is("c68537c6534a02cc2b176ca1549f4ffa190b58ee"));
        assertThat(commit.getMessage(), is("give Caolan credit where it's due (up top)"));
        verifyUtcDate(commit.getAuthoredDate(), 2010, 12, 8, 21, 28, 50);
        verifyUtcDate(commit.getCommittedDate(), 2010, 12, 8, 21, 28, 51);

        // Nested: Commit.Parents
        List<ProjectBranch.Commit.Parent> parents = commit.getParents();
        assertThat(parents, is(notNullValue()));
        assertThat(parents, hasSize(1));
        assertThat(parents.get(0).getId(), is("3f94fc7c85061973edc9906ae170cc269b07ca55"));

        // Nested: Commit.Author
        ProjectBranch.Commit.Author author = commit.getAuthor();
        assertThat(author, is(notNullValue()));
        assertThat(author.getName(), is("Jeremy Ashkenas"));
        assertThat(author.getEmail(), is("jashkenas@example.com"));

        // Nested: Commit.Author
        ProjectBranch.Commit.Committer committer = commit.getCommitter();
        assertThat(committer, is(notNullValue()));
        assertThat(committer.getName(), is("CJeremy Ashkenas"));
        assertThat(committer.getEmail(), is("cjashkenas@example.com"));

    }

}
