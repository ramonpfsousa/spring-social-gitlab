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

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabUser;
import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

import java.io.IOException;

/**
 *
 * @author p.hoeffling
 */
public class GitlabAdapter implements ApiAdapter<GitlabAPI> {

    @Override
    public boolean test(GitlabAPI gitlab) {
        try {
            gitlab.getUser();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(GitlabAPI gitlab, ConnectionValues values) {

        try {
            GitlabUser gitlabUser = gitlab.getUser();
            values.setProviderUserId(Long.toString(gitlabUser.getId()));
            values.setDisplayName(gitlabUser.getUsername());
            values.setImageUrl(gitlabUser.getAvatarUrl());
        } catch (IOException e) {
            throw new ApiException("gitlab?", "Could not fetch current user.", e);
        }
    }

    @Override
    public UserProfile fetchUserProfile(GitlabAPI gitlab) {
        try {
            GitlabUser gitlabUser = gitlab.getUser();

            return new UserProfileBuilder()
                    .setEmail(gitlabUser.getEmail())
                    .setName(gitlabUser.getName())
                    .setUsername(gitlabUser.getUsername())
                    .build();
        } catch (IOException e) {
            throw new ApiException("gitlab?", "Could not fetch current user.", e);
        }
    }

    @Override
    public void updateStatus(GitlabAPI a, String string) {
        // Not provided by this Adapter
    }

}
