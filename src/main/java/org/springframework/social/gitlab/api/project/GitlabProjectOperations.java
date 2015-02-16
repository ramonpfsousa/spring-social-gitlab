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
import org.springframework.util.MultiValueMap;

/**
 *
 * @author p.hoeffling
 */
public interface GitlabProjectOperations {

    public List<Project> getProjectsAccessibleByCurrentUser();

    public List<Project> getProjectsAccessibleByCurrentUser(ListProjectParametersBuilder builder);

    public List<Project> getProjectsAccessibleByCurrentUser(MultiValueMap<String, String> parameters);

    public List<Project> getProjectsOwnedByCurrentUser();

    public List<Project> getProjectsOwnedByCurrentUser(ListProjectParametersBuilder builder);

    public List<Project> getProjectsOwnedByCurrentUser(MultiValueMap<String, String> parameters);

    public Project getProject(long projectId);

    public List<ProjectEvent> getProjectEvents(long projectId);

    public List<ProjectMember> getProjectMembers(long projectId);

    public ProjectMember getProjectMember(long projectId, long userId);

    public List<ProjectHook> getProjectHooks(long projectId);

    public ProjectHook getProjectHook(long projectId, long hookId);
    
    public List<ProjectBranch> getProjectBranches(long projectId);
    
    public ProjectBranch getProjectBranch(long projectId, String branchName);
    
}
