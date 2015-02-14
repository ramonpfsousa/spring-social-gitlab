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
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.gitlab.api.project.GitlabProject;
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
        String url = uriBuilder.builder().pathSegment("projects").toUriString();
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-list"), MediaType.APPLICATION_JSON));
        

        List<GitlabProject> projects = gitlab.projectOperations().getProjectsAccessibleByCurrentUser();
        
        assertThat(projects, hasSize(2));
    }

    @Test
    public void testGetProjectsOwnedByCurrentUser() {
        String url = uriBuilder.builder().pathSegment("projects", "owned").toUriString();
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project-list"), MediaType.APPLICATION_JSON));
        

        List<GitlabProject> projects = gitlab.projectOperations().getProjectsOwnedByCurrentUser();
        
        assertThat(projects, hasSize(2));
    }

    @Test
    public void testGetProject() {
        String url = uriBuilder.builder().pathSegment("projects", "1").toUriString();
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-project"), MediaType.APPLICATION_JSON));
        
      
        GitlabProject project = gitlab.projectOperations().getProject(1);
        
        
    }
    
}
