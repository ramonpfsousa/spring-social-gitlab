package org.springframework.social.gitlab.api.impl;

import java.net.URI;
import org.springframework.social.gitlab.api.GitlabProfileOperations;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.social.gitlab.api.domain.GitlabProfile;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProfileTemplate implements GitlabProfileOperations {

    private final RestOperations restOperations;

    private final GitlabUriBuilder uriBuilder;
    
    public GitlabProfileTemplate(RestOperations restOperations, GitlabUriBuilder uriBuilder) {
        Assert.notNull(uriBuilder, "GitlabUriBuilder can not be null.");
        this.restOperations = restOperations;
        this.uriBuilder = uriBuilder;
    }

    @Override
    public GitlabProfile getProfile() {
        URI uri = uriBuilder.builder().pathSegment("user").build().toUri();
        return restOperations.getForObject(uri, GitlabProfile.class);
    }

    
}
