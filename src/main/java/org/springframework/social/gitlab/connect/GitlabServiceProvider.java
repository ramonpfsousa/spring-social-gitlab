package org.springframework.social.gitlab.connect;

import org.springframework.social.gitlab.api.Gitlab;
import org.springframework.social.gitlab.api.impl.GitlabTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 *
 * @author p.hoeffling
 */
public class GitlabServiceProvider extends AbstractOAuth2ServiceProvider<Gitlab> {

    private final GitlabConfiguration configuration;

    public GitlabServiceProvider(GitlabConfiguration configuration) {
        super(new OAuth2Template(
                configuration.getApplicationId(),
                configuration.getApplicationSecret(),
                configuration.getUriBuilder().oauth().pathSegment("authorize").toUriString(),
                configuration.getUriBuilder().oauth().pathSegment("token").toUriString()
        ));
        this.configuration = configuration;
    }

    public GitlabConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public Gitlab getApi(String accessToken) {
        return new GitlabTemplate(accessToken, configuration.getUriBuilder());
    }

}
