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
        GitlabProfile profile = gitlab.profileOperations().getProfile();
        
        values.setProviderUserId(Long.toString(profile.getId()));
        values.setDisplayName(profile.getUsername());
        values.setImageUrl(profile.getAvatarUrl());
    }

    @Override
    public UserProfile fetchUserProfile(Gitlab gitlab) {
        GitlabProfile profile = gitlab.profileOperations().getProfile();

        return new UserProfileBuilder()
                .setEmail(profile.getEmail())
                .setName(profile.getName())
                .setUsername(profile.getUsername())
                .build();
    }

    @Override
    public void updateStatus(Gitlab a, String string) {
    }

}
