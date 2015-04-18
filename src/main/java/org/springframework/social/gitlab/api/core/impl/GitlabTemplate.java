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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.gitlab.api.Gitlab;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.social.gitlab.api.core.LinkHeaderParser;
import org.springframework.social.gitlab.api.core.PagedList;
import org.springframework.social.gitlab.api.core.Paging;
import org.springframework.social.gitlab.api.core.impl.json.GitlabModule;
import org.springframework.social.gitlab.api.issue.IssueOperations;
import org.springframework.social.gitlab.api.issue.impl.IssueTemplate;
import org.springframework.social.gitlab.api.profile.GitlabProfileOperations;
import org.springframework.social.gitlab.api.profile.impl.GitlabProfileTemplate;
import org.springframework.social.gitlab.api.project.ProjectOperations;
import org.springframework.social.gitlab.api.project.impl.ProjectTemplate;
import org.springframework.social.gitlab.api.user.GitlabUserOperations;
import org.springframework.social.gitlab.api.user.impl.GitlabUserTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author p.hoeffling
 */
public class GitlabTemplate extends AbstractOAuth2ApiBinding implements Gitlab {

    private GitlabProfileOperations profileOperations;

    private GitlabUserOperations userOperations;

    private ProjectOperations projectOperations;

    private IssueOperations issueOperations;

    private final GitlabUriBuilder uriBuilder;

    private final LinkHeaderParser linkHeaderParser;

    public GitlabTemplate(String accessToken, GitlabUriBuilder uriBuilder) {
        super(accessToken);
        Assert.notNull(uriBuilder, "GitlabUriBuilder can not be null.");
        this.uriBuilder = uriBuilder;
        this.linkHeaderParser = new LinkHeaderParser();
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
    public IssueOperations issueOperations() {
        return issueOperations;
    }

    @Override
    public RestOperations restOperations() {
        return this.getRestTemplate();
    }

    @Override
    public GitlabUriBuilder uriBuilder() {
        return this.uriBuilder;
    }

    @Override
    public <T> PagedList<T> getForPage(URI url, Class<T> responseType) {

        ParameterizedTypeReference<List<T>> listType = createTypeReference(responseType);
        ResponseEntity<List<T>> response = restOperations().exchange(url, HttpMethod.GET, HttpEntity.EMPTY, listType);
        Paging paging = linkHeaderParser.buildPaging(response.getHeaders().getFirst("Link"));
        PagedList<T> pagedList = new PagedList<>(response.getBody(), paging);

        return pagedList;
    }

    @Override
    public <T> List<T> getForList(URI url, final Class<T> responseType) {
        ParameterizedTypeReference<List<T>> listType = createTypeReference(responseType);
        ResponseEntity<List<T>> response = restOperations().exchange(url, HttpMethod.GET, HttpEntity.EMPTY, listType);

        return response.getBody();
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        objectMapper.registerModule(new GitlabModule());

        return converter;
    }

    private void initSubApis() {
        this.profileOperations = new GitlabProfileTemplate(this);
        this.userOperations = new GitlabUserTemplate(this);
        this.projectOperations = new ProjectTemplate(this);
        this.issueOperations = new IssueTemplate(this);
    }

    /**
     * Extend ParaeterizedTypeReference with a ParamaterizedType.
     *
     * Resolves the actual type of a list to a Parameterized type.
     *
     * @param <T>
     * @param listItemType
     * @return
     */
    private <T> ParameterizedTypeReference<List<T>> createTypeReference(final Class<T> listItemType) {
        return new ParameterizedTypeReference<List<T>>() {
            @Override
            public Type getType() {
                Type[] actualTypes = {listItemType};
                ParameterizedType listType = new ParameterizedListItemType(
                        List.class,
                        actualTypes
                );
                return listType;
            }
        };
    }

}
