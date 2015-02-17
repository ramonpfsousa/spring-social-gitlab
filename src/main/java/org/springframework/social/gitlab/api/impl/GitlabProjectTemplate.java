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
package org.springframework.social.gitlab.api.impl;

import org.springframework.social.gitlab.api.GitlabProjectOperations;
import org.springframework.social.gitlab.api.ProjectHook;
import org.springframework.social.gitlab.api.ProjectBranchList;
import org.springframework.social.gitlab.api.ProjectEvent;
import org.springframework.social.gitlab.api.Project;
import org.springframework.social.gitlab.api.ProjectMemberList;
import org.springframework.social.gitlab.api.ProjectBranch;
import org.springframework.social.gitlab.api.ProjectEventList;
import org.springframework.social.gitlab.api.ProjectHookList;
import org.springframework.social.gitlab.api.ProjectMember;
import org.springframework.social.gitlab.api.ProjectList;
import java.net.URI;
import java.util.List;
import org.springframework.social.gitlab.api.impl.AbstractGitlabTemplate;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.social.gitlab.api.project.ListProjectParametersBuilder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProjectTemplate extends AbstractGitlabTemplate implements GitlabProjectOperations {

    static final String PROJECTS = "projects";
    static final String OWNED = "owned";
    static final String PROJECT_ID = "{projectId}";
    static final String EVENTS = "events";
    static final String MEMBERS = "members";
    static final String USER_ID = "{userId}";
    static final String HOOKS = "hooks";
    static final String HOOK_ID = "{hookId}";
    static final String BRANCHES = "branches";
    static final String BRANCH_NAME = "{branchName}";
    static final String REPOSITORY = "repository";

    public GitlabProjectTemplate(RestOperations restOperations, GitlabUriBuilder uriBuilder) {
        super(restOperations, uriBuilder);
    }

    @Override
    public List<Project> getProjectsAccessibleByCurrentUser() {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS)
                .build()
                .toUri();
        
        return restOperations.getForObject(uri, ProjectList.class);
    }

    @Override
    public List<Project> getProjectsAccessibleByCurrentUser(MultiValueMap<String, String> parameters) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS)
                .queryParams(parameters)
                .build()
                .toUri();

        return restOperations.getForObject(uri, ProjectList.class);
    }

    @Override
    public List<Project> getProjectsAccessibleByCurrentUser(ListProjectParametersBuilder builder) {
        return getProjectsAccessibleByCurrentUser(builder.build());
    }

    @Override
    public List<Project> getProjectsOwnedByCurrentUser() {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, OWNED)
                .build()
                .toUri();

        return restOperations.getForObject(uri, ProjectList.class);
    }

    @Override
    public List<Project> getProjectsOwnedByCurrentUser(ListProjectParametersBuilder builder) {
        return getProjectsOwnedByCurrentUser(builder.build());
    }

    @Override
    public List<Project> getProjectsOwnedByCurrentUser(MultiValueMap<String, String> parameters) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, OWNED)
                .queryParams(parameters)
                .build()
                .toUri();

        return restOperations.getForObject(uri, ProjectList.class);
    }

    @Override
    public Project getProject(long projectId) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, PROJECT_ID)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, Project.class);
    }

    @Override
    public List<ProjectEvent> getProjectEvents(long projectId) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, PROJECT_ID, EVENTS)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, ProjectEventList.class);
    }

    @Override
    public List<ProjectMember> getProjectMembers(long projectId) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, PROJECT_ID, MEMBERS)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, ProjectMemberList.class);
    }

    @Override
    public ProjectMember getProjectMember(long projectId, long userId) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, PROJECT_ID, MEMBERS, USER_ID)
                .buildAndExpand(projectId, userId)
                .toUri();

        return restOperations.getForObject(uri, ProjectMember.class);
    }

    @Override
    public List<ProjectHook> getProjectHooks(long projectId) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, PROJECT_ID, HOOKS)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, ProjectHookList.class);
    }

    @Override
    public ProjectHook getProjectHook(long projectId, long hookId) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, PROJECT_ID, HOOKS, HOOK_ID)
                .buildAndExpand(projectId, hookId)
                .toUri();

        return restOperations.getForObject(uri, ProjectHook.class);
        
    }

    @Override
    public List<ProjectBranch> getProjectBranches(long projectId) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, PROJECT_ID, REPOSITORY, BRANCHES)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, ProjectBranchList.class);
    }

    @Override
    public ProjectBranch getProjectBranch(long projectId, String branchName) {
        URI uri = uriBuilder.api()
                .pathSegment(PROJECTS, PROJECT_ID, REPOSITORY, BRANCHES, BRANCH_NAME)
                .buildAndExpand(projectId, branchName)
                .toUri();

        return restOperations.getForObject(uri, ProjectBranch.class);
    }

    
    
}
