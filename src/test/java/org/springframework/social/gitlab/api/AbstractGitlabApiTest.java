package org.springframework.social.gitlab.api;

import org.springframework.social.gitlab.api.core.impl.GitlabTemplate;
import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 *
 * @author p.hoeffling
 */
public abstract class AbstractGitlabApiTest {

    protected GitlabTemplate gitlab;

    protected GitlabUriBuilder uriBuilder;

    protected MockRestServiceServer mockServer;

    @Before
    public void setup() {
        uriBuilder = new GitlabUriBuilder();
        gitlab = new GitlabTemplate("ACCESS_TOKEN", uriBuilder);
        mockServer = MockRestServiceServer.createServer(gitlab.getRestTemplate());

    }

    protected Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json", getClass());
    }
}
