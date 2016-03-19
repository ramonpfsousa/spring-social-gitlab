package org.springframework.social.gitlab.api.profile.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import org.springframework.social.gitlab.api.profile.GitlabProfile;
import static org.springframework.social.gitlab.api.utils.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProfileTemplateTest extends AbstractGitlabApiTest {
    

    @Test
    public void testGetProfile() throws Exception {
        String url = uriBuilder.api().pathSegment("user").build()
                .toUriString();
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("gitlab-profile"));
        
        GitlabProfile userProfile = gitlab.profileOperations().getProfile();
        assertThat(userProfile.getId(), is(1L));
        assertThat(userProfile.getUsername(), is("john_smith"));
        assertThat(userProfile.getEmail(), is("john@example.com"));
        assertThat(userProfile.getName(), is("John Smith"));
        assertThat(userProfile.getPrivateToken(), is("dd34asd13as"));
        assertThat(userProfile.getBio(), is("bio"));
        assertThat(userProfile.getSkype(), is("skype"));
        assertThat(userProfile.getLinkedin(), is("linkedin"));
        assertThat(userProfile.getTwitter(), is("twitter"));
        assertThat(userProfile.getWebsiteUrl(), is("website_url"));
        assertThat(userProfile.getAvatarUrl(), is("http://localhost:3000/uploads/user/avatar/1/cd8.jpeg"));
        
        assertThat(userProfile.getThemeId(), is(1L));
        assertThat(userProfile.getColorSchemeId(), is(2L));
        assertThat(userProfile.getProjectsLimit(), is(100L));
        
        assertThat(userProfile.isAdmin(), is(false));
        assertThat(userProfile.canCreateGroup(), is(true));
        assertThat(userProfile.canCreateProject(), is(true));

        verifyUtcDate(userProfile.getCreatedAt(), 2012, 5, 23, 8, 0, 58);
    }
    
}
