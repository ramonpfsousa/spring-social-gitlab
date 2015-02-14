package org.springframework.social.gitlab.api.user;

import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.gitlab.api.user.GitlabSSHKey;
import org.springframework.social.gitlab.api.user.GitlabUser;
import static org.springframework.social.gitlab.api.user.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 *
 * @author p.hoeffling
 */
public class GitlabUserTemplateTest extends AbstractGitlabApiTest {
    

    @Test
    public void testGetUserById() throws Exception {
        String url = uriBuilder.builder().pathSegment("users", "1").toUriString();
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-user"), MediaType.APPLICATION_JSON));
        
        GitlabUser user = gitlab.userOperations().getUser(1);
        
        assertThat(user.getId(), is(1L));
        assertThat(user.getUsername(), is("john_smith"));
        assertThat(user.getName(), is("John Smith"));
        assertThat(user.getState(), is("active"));
        assertThat(user.getAvatarUrl(), is("http://localhost:3000/uploads/user/avatar/1/cd8.jpeg"));
    }
    
    @Test
    public void testGetCurrentUser() throws Exception {
        String url = uriBuilder.builder().pathSegment("user").toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                // Current user returns profile JSON!
                .andRespond(withSuccess(jsonResource("gitlab-profile"), MediaType.APPLICATION_JSON));
        
        GitlabUser user = gitlab.userOperations().getCurrentUser();
        
        assertThat(user.getId(), is(1L));
        assertThat(user.getUsername(), is("john_smith"));
        assertThat(user.getName(), is("John Smith"));
        assertThat(user.getState(), is("active"));
        assertThat(user.getAvatarUrl(), is("http://localhost:3000/uploads/user/avatar/1/cd8.jpeg"));
    }
    
    @Test
    public void testGetCurrentUsersSSHKeys() {
        String url = uriBuilder.builder().pathSegment("user", "keys").toUriString();
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-sshkey-list"), MediaType.APPLICATION_JSON));
        
        List<GitlabSSHKey> currentUsersSSHKeys = gitlab.userOperations().getCurrentUsersSSHKeys();
        
        assertThat(currentUsersSSHKeys, hasSize(2));
    }
    
    @Test
    public void testGetCurrentUsersSSHKey() {
        String url = uriBuilder.builder().pathSegment("user", "keys", "1").toUriString();
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("gitlab-sshkey"), MediaType.APPLICATION_JSON));
        
        GitlabSSHKey sshKey = gitlab.userOperations().getCurrentUsersSSHKey(1);
        
        assertThat(sshKey.getId(), is(1L));
        assertThat(sshKey.getTitle(), is("Public key"));
        assertThat(sshKey.getKey(), is("ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAIEAiPWx6WM4lhHNedGfBpPJNPpZ7yKu+dnn1SJejgt4596k6YjzGGphH2TUxwKzxcKDKKezwkpfnxPkSMkuEspGRt/aZZ9wa++Oi7Qkr8prgHc4soW6NUlfDzpvZK2H5E7eQaSeP3SAwGmQKUFHCddNaP0L+hM7zhFNzjFvpaMgJw0="));
        verifyUtcDate(sshKey.getCreatedAt(), 2014, 8, 1, 14, 47, 39);
    }
}
