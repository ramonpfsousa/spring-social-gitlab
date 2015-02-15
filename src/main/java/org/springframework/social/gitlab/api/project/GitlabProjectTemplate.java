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
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProjectTemplate extends AbstractGitlabTemplate implements GitlabProjectOperations {

    static final String SEGMENT_PROJECTS = "projects";

    static final String SEGMENT_OWNED = "owned";

    static final String SEGMENT_PROJECT_ID = "{projectId}";
    
    static final String SEGMENT_EVENTS = "events";

    public GitlabProjectTemplate(RestOperations restOperations, GitlabUriBuilder uriBuilder) {
        super(restOperations, uriBuilder);
    }

    @Override
    public List<GitlabProject> getProjectsAccessibleByCurrentUser() {
        URI uri = uriBuilder.builder().pathSegment(SEGMENT_PROJECTS).build().toUri();
        return restOperations.getForObject(uri, GitlabProjectList.class);
    }

    @Override
    public List<GitlabProject> getProjectsAccessibleByCurrentUser(MultiValueMap<String, String> parameters) {
        UriComponentsBuilder builder = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS);
        
        if (parameters != null) {
            builder.queryParams(parameters);
        }

        return restOperations.getForObject(builder.build().toUri(), GitlabProjectList.class);
    }

    @Override
    public List<GitlabProject> getProjectsAccessibleByCurrentUser(ListProjectParametersBuilder builder) {
        return getProjectsAccessibleByCurrentUser(builder.build());
    }

    @Override
    public List<GitlabProject> getProjectsOwnedByCurrentUser() {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_OWNED)
                .build()
                .toUri();

        return restOperations.getForObject(uri, GitlabProjectList.class);
    }

    @Override
    public GitlabProject getProject(long projectId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID)
                .buildAndExpand(projectId)
                .toUri();
        
        return restOperations.getForObject(uri, GitlabProject.class);
    }

    @Override
    public List<GitlabProjectEvent> getProjectEvents(long projectId) {
        URI uri = uriBuilder.builder()
                .pathSegment(SEGMENT_PROJECTS, SEGMENT_PROJECT_ID, SEGMENT_EVENTS)
                .buildAndExpand(projectId)
                .toUri();
        
        return restOperations.getForObject(uri, GitlabProjectEventList.class);
    }

}
