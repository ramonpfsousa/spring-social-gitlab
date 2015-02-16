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

import java.net.URI;
import java.util.List;
import org.springframework.social.gitlab.api.AbstractGitlabTemplate;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProjectTemplate extends AbstractGitlabTemplate implements GitlabProjectOperations {

    static final String SEGMENT_PROJECTS = "projects";
    static final String SEGMENT_OWNED = "owned";
    static final String SEGMENT_PROJECT_ID = "{projectId}";
    static final String SEGMENT_EVENTS = "events";
    static final String SEGMENT_MEMBERS = "members";
    static final String SEGMENT_USER_ID = "{userId}";
    static final String SEGMENT_HOOKS = "hooks";
    static final String SEGMENT_HOOK_ID = "{hookId}";
    static final String SEGMENT_BRANCHES = "branches";
    static final String SEGMENT_BRANCH_NAME = "{branchName}";

    public GitlabProjectTemplate(RestOperations restOperations, GitlabUriBuilder uriBuilder) {
        super(restOperations, uriBuilder);
    }

    @Override
    public List<Project> getProjectsAccessibleByCurrentUser() {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS)
                .build()
                .toUri();
        
        return restOperations.getForObject(uri, ProjectList.class);
    }

    @Override
    public List<Project> getProjectsAccessibleByCurrentUser(MultiValueMap<String, String> parameters) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS)
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
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_OWNED)
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
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_OWNED)
                .queryParams(parameters)
                .build()
                .toUri();

        return restOperations.getForObject(uri, ProjectList.class);
    }

    @Override
    public Project getProject(long projectId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, Project.class);
    }

    @Override
    public List<ProjectEvent> getProjectEvents(long projectId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID, SEGMENT_EVENTS)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, ProjectEventList.class);
    }

    @Override
    public List<ProjectMember> getProjectMembers(long projectId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID, SEGMENT_MEMBERS)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, ProjectMemberList.class);
    }

    @Override
    public ProjectMember getProjectMember(long projectId, long userId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID, SEGMENT_MEMBERS, SEGMENT_USER_ID)
                .buildAndExpand(projectId, userId)
                .toUri();

        return restOperations.getForObject(uri, ProjectMember.class);
    }

    @Override
    public List<ProjectHook> getProjectHooks(long projectId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID, SEGMENT_HOOKS)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, ProjectHookList.class);
    }

    @Override
    public ProjectHook getProjectHook(long projectId, long hookId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID, SEGMENT_HOOKS, SEGMENT_HOOK_ID)
                .buildAndExpand(projectId, hookId)
                .toUri();

        return restOperations.getForObject(uri, ProjectHook.class);
        
    }

    @Override
    public List<ProjectBranch> getProjectBranches(long projectId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID, SEGMENT_REPOSITORY, SEGMENT_BRANCHES)
                .buildAndExpand(projectId)
                .toUri();

        return restOperations.getForObject(uri, ProjectBranchList.class);
    }

    @Override
    public ProjectBranch getProjectBranch(long projectId, String branchName) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID, SEGMENT_REPOSITORY, SEGMENT_BRANCHES, SEGMENT_BRANCH_NAME)
                .buildAndExpand(projectId, branchName)
                .toUri();

        return restOperations.getForObject(uri, ProjectBranch.class);
    }
    static final String SEGMENT_REPOSITORY = "repository";

    
    
}
