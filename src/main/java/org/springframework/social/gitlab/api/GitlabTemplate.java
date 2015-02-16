package org.springframework.social.gitlab.api;

import org.springframework.social.gitlab.api.profile.GitlabProfileOperations;
import org.springframework.social.gitlab.api.profile.GitlabProfileTemplate;
import org.springframework.social.gitlab.api.project.GitlabProjectOperations;
import org.springframework.social.gitlab.api.project.GitlabProjectTemplate;
import org.springframework.social.gitlab.api.user.GitlabUserOperations;
import org.springframework.social.gitlab.api.user.GitlabUserTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.util.Assert;

/**
 *
 * @author p.hoeffling
 */
public class GitlabTemplate extends AbstractOAuth2ApiBinding implements Gitlab {

    private GitlabProfileOperations profileOperations;
 
    private GitlabUserOperations userOperations;
    
    private GitlabProjectOperations projectOperations;
    
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
    public GitlabProjectOperations projectOperations() {
        return projectOperations;
    }

    
    
    private void initSubApis() {
        this.profileOperations = new GitlabProfileTemplate(getRestTemplate(), uriBuilder);
        this.userOperations = new GitlabUserTemplate(getRestTemplate(), uriBuilder);
        this.projectOperations = new GitlabProjectTemplate(getRestTemplate(), uriBuilder);
    }
}
