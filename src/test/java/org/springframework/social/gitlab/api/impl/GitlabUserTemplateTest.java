package org.springframework.social.gitlab.api.impl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.gitlab.api.domain.GitlabProfile;
import org.springframework.social.gitlab.api.domain.GitlabUser;
import static org.springframework.social.gitlab.api.impl.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 *
 * @author p.hoeffling
 */
public class GitlabUserTemplateTest extends AbstractGitlabApiTest {
    

    @Test
    public void testGetProfile() throws Exception {
        String url = uriBuilder.builder().pathSegment("users", "1").toUriString();
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-user"), MediaType.APPLICATION_JSON));
        
        GitlabUser user = gitlab.userOperations().getUser(1);
        
        assertEquals(1, user.getId());
        assertEquals("john_smith", user.getUsername());
        assertEquals("John Smith", user.getName());
        assertEquals("active", user.getState());
        assertEquals("http://localhost:3000/uploads/user/avatar/1/cd8.jpeg", user.getAvatarUrl());
    }
    
}
