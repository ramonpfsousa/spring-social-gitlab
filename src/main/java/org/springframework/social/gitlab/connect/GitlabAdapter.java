package org.springframework.social.gitlab.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.gitlab.api.Gitlab;
import org.springframework.social.gitlab.api.profile.GitlabProfile;

/**
 *
 * @author p.hoeffling
 */
public class GitlabAdapter implements ApiAdapter<Gitlab> {

    @Override
    public boolean test(Gitlab gitlab) {
        try {
            gitlab.profileOperations().getProfile();
            return true;
        } catch (ApiException e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(Gitlab gitlab, ConnectionValues values) {
        GitlabProfile gitlabProfile = gitlab.profileOperations().getProfile();
        values.setProviderUserId(Long.toString(gitlabProfile.getId()));
        values.setDisplayName(gitlabProfile.getUsername());
        values.setImageUrl(gitlabProfile.getAvatarUrl());
    }

    @Override
    public UserProfile fetchUserProfile(Gitlab gitlab) {
        GitlabProfile gitlabProfile = gitlab.profileOperations().getProfile();

        return new UserProfileBuilder()
                .setEmail(gitlabProfile.getEmail())
                .setName(gitlabProfile.getName())
                .setUsername(gitlabProfile.getUsername())
                .build();
    }

    @Override
    public void updateStatus(Gitlab a, String string) {
    }

}
