package org.springframework.social.gitlab.api.profile.impl;

import org.springframework.social.gitlab.api.profile.GitlabProfile;
import org.springframework.social.gitlab.api.profile.GitlabProfileOperations;
import java.net.URI;
import org.springframework.social.gitlab.api.core.impl.AbstractGitlabOperations;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.social.gitlab.api.core.impl.AbstractGitlabOperations;
import org.springframework.social.gitlab.api.profile.GitlabProfile;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProfileTemplate extends AbstractGitlabOperations implements GitlabProfileOperations {

    public GitlabProfileTemplate(RestOperations restOperations, GitlabUriBuilder uriBuilder) {
        super(restOperations, uriBuilder);
    }

    @Override
    public GitlabProfile getProfile() {
        URI uri = uriBuilder.api().pathSegment("user").build().toUri();
        return restOperations.getForObject(uri, GitlabProfile.class);
    }

    
}
