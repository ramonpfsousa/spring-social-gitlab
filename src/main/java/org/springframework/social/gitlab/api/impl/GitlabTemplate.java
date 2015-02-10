package org.springframework.social.gitlab.api.impl;

import org.springframework.social.gitlab.api.Gitlab;
import org.springframework.social.gitlab.api.GitlabProfileOperations;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.util.Assert;

/**
 *
 * @author p.hoeffling
 */
public class GitlabTemplate extends AbstractOAuth2ApiBinding implements Gitlab {

    private GitlabProfileOperations profileOperations;
 
    private final GitlabUriBuilder uriBuilder;
    
    public GitlabTemplate(String accessToken) {
        super(accessToken);
        this.uriBuilder = new GitlabUriBuilder();
        initSubApis();
    }

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

    
    private void initSubApis() {
        this.profileOperations = new GitlabProfileTemplate(getRestTemplate(), uriBuilder);
    }
}
