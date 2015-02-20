package org.springframework.social.gitlab.api.profile.impl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import org.springframework.social.gitlab.api.profile.GitlabProfile;
import static org.springframework.social.gitlab.api.utils.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProfileTemplateTest extends AbstractGitlabApiTest {
    

    @Test
    public void testGetProfile() throws Exception {
        String url = uriBuilder.api().pathSegment("user").toUriString();
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-profile"), MediaType.APPLICATION_JSON));
        
        GitlabProfile userProfile = gitlab.profileOperations().getProfile();
        assertEquals(1, userProfile.getId());
        assertEquals("john_smith", userProfile.getUsername());
        assertEquals("john@example.com", userProfile.getEmail());
        assertEquals("John Smith", userProfile.getName());
        assertEquals("dd34asd13as", userProfile.getPrivateToken());
        assertEquals("active", userProfile.getState());
        verifyUtcDate(userProfile.getCreatedAt(), 2012, 5, 23, 8, 0, 58);
        assertEquals("bio", userProfile.getBio());
        assertEquals("skype", userProfile.getSkype());
        assertEquals("linkedin", userProfile.getLinkedin());
        assertEquals("twitter", userProfile.getTwitter());
        assertEquals("website_url", userProfile.getWebsiteUrl());
        assertEquals("http://localhost:3000/uploads/user/avatar/1/cd8.jpeg", userProfile.getAvatarUrl());
        assertEquals(1, userProfile.getThemeId());
        assertEquals(2, userProfile.getColorSchemeId());
        assertEquals(false, userProfile.isAdmin());
        assertEquals(true, userProfile.canCreateGroup());
        assertEquals(true, userProfile.canCreateProject());
        assertEquals(100, userProfile.getProjectsLimit());
        
    }
    
}
