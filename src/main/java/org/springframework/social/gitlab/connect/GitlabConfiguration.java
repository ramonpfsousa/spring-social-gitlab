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
package org.springframework.social.gitlab.connect;

import org.springframework.core.env.PropertyResolver;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.util.Assert;

/**
 *
 * @author p.hoeffling
 */
public class GitlabConfiguration {

    public static GitlabConfiguration fromProperties(String applicationId, String applicationSecret, String baseUrl, String apiPath) {
        if (baseUrl == null) {
            baseUrl = GitlabUriBuilder.DEFAULT_URL;
        }
        if (apiPath == null) {
            apiPath = GitlabUriBuilder.DEFAULT_API_PATH;
        }        
        return new GitlabConfiguration(applicationId, applicationSecret, baseUrl, apiPath);
    }
    
    public static GitlabConfiguration fromProperties(PropertyResolver propertyResolver) {
        return fromProperties(
                propertyResolver.getRequiredProperty("spring.social.gitlab.app-id"),
                propertyResolver.getRequiredProperty("spring.social.gitlab.app-secret"),
                propertyResolver.getProperty("spring.social.gitlab.base-url"),
                propertyResolver.getProperty("spring.social.gitlab.api-path")
        );
    }
    
    private final String applicationId;
    
    private final String applicationSecret;
    
    private final GitlabUriBuilder uriBuilder;
    
    public GitlabConfiguration(String applicationId, String applicationSecret, String baseUrl, String apiPath) {
        this.applicationId = applicationId;
        this.applicationSecret = applicationSecret;
        this.uriBuilder = new GitlabUriBuilder(baseUrl, apiPath);
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getApplicationSecret() {
        return applicationSecret;
    }

    public GitlabUriBuilder getUriBuilder() {
        return uriBuilder;
    }

    
}