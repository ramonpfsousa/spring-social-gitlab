/*
 * Copyright 2015 p.hoeffling.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.gitlab.connect;

import org.springframework.social.gitlab.api.Gitlab;
import org.springframework.social.gitlab.api.core.impl.GitlabTemplate;
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
                configuration.getUriBuilder().oauth().pathSegment("authorize").build().toUriString(),
                configuration.getUriBuilder().oauth().pathSegment("token").build().toUriString()
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
