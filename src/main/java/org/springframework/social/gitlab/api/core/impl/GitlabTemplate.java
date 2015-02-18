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
package org.springframework.social.gitlab.api.core.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.gitlab.api.Gitlab;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.social.gitlab.api.core.impl.json.GitlabModule;
import org.springframework.social.gitlab.api.profile.GitlabProfileOperations;
import org.springframework.social.gitlab.api.profile.impl.GitlabProfileTemplate;
import org.springframework.social.gitlab.api.project.ProjectOperations;
import org.springframework.social.gitlab.api.project.impl.ProjectTemplate;
import org.springframework.social.gitlab.api.user.GitlabUserOperations;
import org.springframework.social.gitlab.api.user.impl.GitlabUserTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.util.Assert;

/**
 *
 * @author p.hoeffling
 */
public class GitlabTemplate extends AbstractOAuth2ApiBinding implements Gitlab {

    private GitlabProfileOperations profileOperations;

    private GitlabUserOperations userOperations;

    private ProjectOperations projectOperations;

    private final GitlabUriBuilder uriBuilder;

    public GitlabTemplate(String accessToken, GitlabUriBuilder uriBuilder) {
        super(accessToken);
        Assert.notNull(uriBuilder, "GitlabUriBuilder can not be null.");
        this.uriBuilder = uriBuilder;
        initSubApis();
    }

    @Override
    public GitlabProfileOperations profileOperations() {
        return profileOperations;
    }

    @Override
    public GitlabUserOperations userOperations() {
        return userOperations;
    }

    @Override
    public ProjectOperations projectOperations() {
        return projectOperations;
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        objectMapper.registerModule(new GitlabModule());

        return converter;
    }

    private void initSubApis() {
        this.profileOperations = new GitlabProfileTemplate(getRestTemplate(), uriBuilder);
        this.userOperations = new GitlabUserTemplate(getRestTemplate(), uriBuilder);
        this.projectOperations = new ProjectTemplate(getRestTemplate(), uriBuilder);
    }
}
