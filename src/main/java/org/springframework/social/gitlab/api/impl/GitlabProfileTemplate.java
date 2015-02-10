package org.springframework.social.gitlab.api.impl;

import java.net.URI;
import org.springframework.social.gitlab.api.GitlabProfileOperations;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.social.gitlab.api.domain.GitlabProfile;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProfileTemplate extends AbstractGitlabTemplate implements GitlabProfileOperations {

    public GitlabProfileTemplate(RestOperations restOperations, GitlabUriBuilder uriBuilder) {
        super(restOperations, uriBuilder);
    }

    @Override
    public GitlabProfile getProfile() {
        URI uri = uriBuilder.builder().pathSegment("user").build().toUri();
        return restOperations.getForObject(uri, GitlabProfile.class);
    }

    
}
