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

import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author p.hoeffling
 */
public class GitlabUriBuilder {

    public static final String DEFAULT_URL = "https://gitlab.com/";

    public static final String DEFAULT_API_PATH = "/api/v3";

    private final String baseUrl;

    private final String apiPath;

    public GitlabUriBuilder() {
        this.baseUrl = DEFAULT_URL;
        this.apiPath = DEFAULT_API_PATH;
    }

    public GitlabUriBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
        this.apiPath = DEFAULT_API_PATH;
    }

    public GitlabUriBuilder(String baseUrl, String apiPath) {
        this.baseUrl = baseUrl;
        this.apiPath = apiPath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiPath() {
        return apiPath;
    }
    
    public UriComponentsBuilder oauth() {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("oauth");
    }

    public UriComponentsBuilder api() {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(apiPath);
    }

}
