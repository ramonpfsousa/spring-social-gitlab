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

import java.net.URI;
import java.util.List;
import org.springframework.social.gitlab.api.GitlabApiBinding;
import org.springframework.social.gitlab.api.core.PagedList;
import org.springframework.social.gitlab.api.core.impl.AbstractGitlabOperations;
import org.springframework.social.gitlab.api.project.ListProjectParametersBuilder;
import org.springframework.social.gitlab.api.project.Project;
import org.springframework.social.gitlab.api.project.ProjectBranch;
import org.springframework.social.gitlab.api.project.ProjectBranchList;
import org.springframework.social.gitlab.api.project.ProjectEvent;
import org.springframework.social.gitlab.api.project.ProjectEventList;
import org.springframework.social.gitlab.api.project.ProjectHook;
import org.springframework.social.gitlab.api.project.ProjectHookList;
import org.springframework.social.gitlab.api.project.ProjectList;
import org.springframework.social.gitlab.api.project.ProjectMember;
import org.springframework.social.gitlab.api.project.ProjectMemberList;
import org.springframework.social.gitlab.api.project.ProjectOperations;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author p.hoeffling
 */
public class ProjectTemplate extends AbstractGitlabOperations implements ProjectOperations {

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

    public ProjectTemplate(GitlabApiBinding gitlabApiBinding) {
        super(gitlabApiBinding);
    }

    @Override
    public PagedList<Project> getProjectsAccessibleByCurrentUser() {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS)
                .build()
                .toUri();

        return gitlabApiBinding.getForPage(uri, Project.class);
    }

    @Override
    public PagedList<Project> getProjectsAccessibleByCurrentUser(MultiValueMap<String, String> parameters) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS)
                .queryParams(parameters)
                .build()
                .toUri();

        return gitlabApiBinding.getForPage(uri, Project.class);
    }

    @Override
    public PagedList<Project> getProjectsAccessibleByCurrentUser(ListProjectParametersBuilder builder) {
        return getProjectsAccessibleByCurrentUser(builder.build());
    }

    @Override
    public PagedList<Project> getProjectsOwnedByCurrentUser() {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, OWNED)
                .build()
                .toUri();

        return gitlabApiBinding.getForPage(uri, Project.class);
    }

    @Override
    public PagedList<Project> getProjectsOwnedByCurrentUser(ListProjectParametersBuilder builder) {
        return getProjectsOwnedByCurrentUser(builder.build());
    }

    @Override
    public PagedList<Project> getProjectsOwnedByCurrentUser(MultiValueMap<String, String> parameters) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, OWNED)
                .queryParams(parameters)
                .build()
                .toUri();

        return gitlabApiBinding.getForPage(uri, Project.class);
    }

    @Override
    public Project getProject(long projectId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID)
                .buildAndExpand(projectId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, Project.class);
    }

    @Override
    public List<ProjectEvent> getProjectEvents(long projectId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, EVENTS)
                .buildAndExpand(projectId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, ProjectEventList.class);
    }

    @Override
    public List<ProjectMember> getProjectMembers(long projectId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, MEMBERS)
                .buildAndExpand(projectId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, ProjectMemberList.class);
    }

    @Override
    public ProjectMember getProjectMember(long projectId, long userId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, MEMBERS, USER_ID)
                .buildAndExpand(projectId, userId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, ProjectMember.class);
    }

    @Override
    public List<ProjectHook> getProjectHooks(long projectId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, HOOKS)
                .buildAndExpand(projectId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, ProjectHookList.class);
    }

    @Override
    public ProjectHook getProjectHook(long projectId, long hookId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, HOOKS, HOOK_ID)
                .buildAndExpand(projectId, hookId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, ProjectHook.class);

    }

    @Override
    public List<ProjectBranch> getProjectBranches(long projectId) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, REPOSITORY, BRANCHES)
                .buildAndExpand(projectId)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, ProjectBranchList.class);
    }

    @Override
    public ProjectBranch getProjectBranch(long projectId, String branchName) {
        URI uri = gitlabApiBinding.uriBuilder().api()
                .pathSegment(PROJECTS, PROJECT_ID, REPOSITORY, BRANCHES, BRANCH_NAME)
                .buildAndExpand(projectId, branchName)
                .toUri();

        return gitlabApiBinding.restOperations().getForObject(uri, ProjectBranch.class);
    }

}
