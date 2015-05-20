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
import org.springframework.social.gitlab.api.core.PagedList;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author p.hoeffling
 */
public interface ProjectOperations {

    PagedList<Project> getProjectsAccessibleByCurrentUser();

    PagedList<Project> getProjectsAccessibleByCurrentUser(ListProjectParametersBuilder builder);

    PagedList<Project> getProjectsAccessibleByCurrentUser(MultiValueMap<String, String> parameters);

    PagedList<Project> getProjectsOwnedByCurrentUser();

    PagedList<Project> getProjectsOwnedByCurrentUser(ListProjectParametersBuilder builder);

    PagedList<Project> getProjectsOwnedByCurrentUser(MultiValueMap<String, String> parameters);

    Project getProject(long projectId);

    List<ProjectEvent> getProjectEvents(long projectId);

    List<ProjectMember> getProjectMembers(long projectId);

    ProjectMember getProjectMember(long projectId, long userId);

    List<ProjectHook> getProjectHooks(long projectId);

    ProjectHook getProjectHook(long projectId, long hookId);
    
    List<ProjectBranch> getProjectBranches(long projectId);
    
    ProjectBranch getProjectBranch(long projectId, String branchName);

    List<ProjectTree> getProjecTree(long projectId, String path, String refName);
}
