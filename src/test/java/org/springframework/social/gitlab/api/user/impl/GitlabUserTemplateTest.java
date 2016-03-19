package org.springframework.social.gitlab.api.user.impl;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import org.springframework.social.gitlab.api.user.GitlabUser;
import org.springframework.social.gitlab.api.user.UserKey;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.social.gitlab.api.utils.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

/**
 *
 * @author p.hoeffling
 */
public class GitlabUserTemplateTest extends AbstractGitlabApiTest {

    @Test
    public void testGetUserById() throws Exception {
        String url = uriBuilder.api().pathSegment("users", "1").build()
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("gitlab-user"));

        GitlabUser user = gitlab.userOperations().getUser(1);

        assertThat(user.getId(), is(1L));
        assertThat(user.getUsername(), is("john_smith"));
        assertThat(user.getName(), is("John Smith"));
        assertThat(user.getState(), is("active"));
        assertThat(user.getAvatarUrl(), is("http://localhost:3000/uploads/user/avatar/1/cd8.jpeg"));
    }

    @Test
    public void testGetCurrentUser() throws Exception {
        String url = uriBuilder.api().pathSegment("user").build()
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                // Current user returns profile JSON!
                .andRespond(withJsonResourceSuccess("gitlab-user-profile"));

        GitlabUser user = gitlab.userOperations().getCurrentUser();

        assertThat(user.getId(), is(1L));
        assertThat(user.getUsername(), is("john_smith"));
        assertThat(user.getName(), is("John Smith"));
        assertThat(user.getState(), is("active"));
        assertThat(user.getAvatarUrl(), is("http://localhost:3000/uploads/user/avatar/1/cd8.jpeg"));
    }

    @Test
    public void testGetCurrentUsersSSHKeys() {
        String url = uriBuilder.api().pathSegment("user", "keys").build()
                .toUriString();
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("gitlab-sshkey-list"));

        List<UserKey> currentUsersSSHKeys = gitlab.userOperations().getCurrentUsersSSHKeys();

        assertThat(currentUsersSSHKeys, hasSize(2));
        assertThat(currentUsersSSHKeys.get(0), instanceOf(UserKey.class));
    }

    @Test
    public void testGetCurrentUsersSSHKey() {
        String url = uriBuilder.api().pathSegment("user", "keys", "1").build()
                .toUriString();
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("gitlab-sshkey"));

        UserKey sshKey = gitlab.userOperations().getCurrentUsersSSHKey(1);

        assertThat(sshKey.getId(), is(1L));
        assertThat(sshKey.getTitle(), is("Public key"));
        assertThat(sshKey.getKey(), is("ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAIEAiPWx6WM4lhHNedGfBpPJNPpZ7yKu+dnn1SJejgt4596k6YjzGGphH2TUxwKzxcKDKKezwkpfnxPkSMkuEspGRt/aZZ9wa++Oi7Qkr8prgHc4soW6NUlfDzpvZK2H5E7eQaSeP3SAwGmQKUFHCddNaP0L+hM7zhFNzjFvpaMgJw0="));
        verifyUtcDate(sshKey.getCreatedAt(), 2014, 8, 1, 14, 47, 39);
    }
}
