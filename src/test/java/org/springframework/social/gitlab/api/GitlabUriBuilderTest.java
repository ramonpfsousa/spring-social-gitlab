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
package org.springframework.social.gitlab.api;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author p.hoeffling
 */
public class GitlabUriBuilderTest {
    
    private GitlabUriBuilder builder;
    
    @Before
    public void setup() {
        builder = new GitlabUriBuilder();
    }
    
 
    @Test
    public void testConstructors() {
        
        GitlabUriBuilder localBuilder;
        
        localBuilder = new GitlabUriBuilder();
        assertThat(localBuilder.getBaseUrl(), is(GitlabUriBuilder.DEFAULT_URL));
        assertThat(localBuilder.getApiPath(), is(GitlabUriBuilder.DEFAULT_API_PATH));
        
        localBuilder = new GitlabUriBuilder("http://localhost:8080");
        assertThat(localBuilder.getBaseUrl(), is("http://localhost:8080"));
        assertThat(localBuilder.getApiPath(), is(GitlabUriBuilder.DEFAULT_API_PATH));
        
        localBuilder = new GitlabUriBuilder("http://localhost:8080", "/api/foo");
        assertThat(localBuilder.getBaseUrl(), is("http://localhost:8080"));
        assertThat(localBuilder.getApiPath(), is("/api/foo"));
        
    }
    
    @Test
    public void testBuilderCreation() {
        
        
        
    }
}
