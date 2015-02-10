package org.springframework.social.gitlab.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.gitlab.api.Gitlab;

/**
 *
 * @author p.hoeffling
 */
public class GitlabConnectionFactory extends OAuth2ConnectionFactory<Gitlab> {

    public GitlabConnectionFactory(String clientId, String clientSecret) {
        super(
            "gitlab", 
            new GitlabServiceProvider(clientId, clientSecret), 
            new GitlabAdapter()
        );
    }

    
    
}
