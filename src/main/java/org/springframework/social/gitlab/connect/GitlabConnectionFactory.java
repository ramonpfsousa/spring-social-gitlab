package org.springframework.social.gitlab.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.gitlab.api.Gitlab;

/**
 *
 * @author p.hoeffling
 */
public class GitlabConnectionFactory extends OAuth2ConnectionFactory<Gitlab> {

    public GitlabConnectionFactory(GitlabConfiguration configuration) {
        super(
            "gitlab", 
            new GitlabServiceProvider(configuration), 
            new GitlabAdapter()
        );
    }

    
    
}
