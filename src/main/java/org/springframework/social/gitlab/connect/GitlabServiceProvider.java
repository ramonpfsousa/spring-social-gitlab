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

import org.gitlab.api.AuthMethod;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.TokenType;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 *
 * @author p.hoeffling
 */
public class GitlabServiceProvider extends AbstractOAuth2ServiceProvider<GitlabAPI> {

    private final GitlabConfiguration configuration;

    public GitlabServiceProvider(GitlabConfiguration configuration) {
        super(new OAuth2Template(
                configuration.getApplicationId(),
                configuration.getApplicationSecret(),
                configuration.getAuthorizeUrl(),
                configuration.getAccessTokenUrl()
        ));
        this.configuration = configuration;
    }

    public GitlabConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public GitlabAPI getApi(String accessToken) {
        return GitlabAPI.connect(configuration.getHostUrl(), accessToken, TokenType.ACCESS_TOKEN, AuthMethod.HEADER);
    }

}
