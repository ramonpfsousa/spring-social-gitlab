package org.springframework.social.gitlab.connect;

import org.springframework.social.gitlab.api.Gitlab;
import org.springframework.social.gitlab.api.GitlabTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 *
 * @author p.hoeffling
 */
public class GitlabServiceProvider extends AbstractOAuth2ServiceProvider<Gitlab> {

    public GitlabServiceProvider(String clientId, String clientSecret) {
        super(new OAuth2Template(
                clientId, 
                clientSecret, 
                "https://gitlab.com/oauth/authorize",
                "https://gitlab.com/oauth/token"
        ));
    }

    @Override
    public Gitlab getApi(String accessToken) {
        return new GitlabTemplate(accessToken);
    }
    
    
    
}
