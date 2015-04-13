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

import java.net.URI;
import org.springframework.social.gitlab.api.GitlabApiBinding;
import org.springframework.social.gitlab.api.core.PagedList;
import org.springframework.social.gitlab.api.core.impl.AbstractGitlabOperations;
import org.springframework.social.gitlab.api.issue.Issue;
import org.springframework.social.gitlab.api.issue.IssueOperations;

/**
 *
 * @author p.hoeffling
 */
public class IssueTemplate extends AbstractGitlabOperations implements IssueOperations {

    static final String ISSUES = "issues";
    static final String ISSUE_ID = "{issueId}";

    public IssueTemplate(GitlabApiBinding gitlabApiBinding) {
        super(gitlabApiBinding);
    }

    @Override
    public PagedList<Issue> getIssuesCreatedByCurrentUser() {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(ISSUES)
                .build()
                .toUri();

        return gitlabApiBinding.getForPage(uri, Issue.class);
    }

    @Override
    public PagedList<Issue> getProjectIssues(long projectId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment("projects", "{projectId}", ISSUES)
                .buildAndExpand(projectId)
                .toUri();

        return gitlabApiBinding.getForPage(uri, Issue.class);
    }

    @Override
    public Issue getProjectIssue(long projectId, long issueId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment("projects", "{projectId}", ISSUES, ISSUE_ID)
                .buildAndExpand(projectId, issueId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, Issue.class);
    }

}
