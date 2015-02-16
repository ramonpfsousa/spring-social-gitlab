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
