package org.springframework.social.gitlab.api;

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.gitlab.api.core.DefaultGitlabUriBuilder;
import org.springframework.social.gitlab.api.core.impl.GitlabTemplate;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;

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
        uriBuilder = new DefaultGitlabUriBuilder();
        gitlab = new GitlabTemplate("ACCESS_TOKEN", uriBuilder);
        mockServer = MockRestServiceServer.createServer(gitlab.getRestTemplate());

    }

    protected Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json", getClass());
    }

    protected DefaultResponseCreator withJsonResourceSuccess(String filename) {
        return MockRestResponseCreators.withSuccess(jsonResource(filename), MediaType.APPLICATION_JSON);
    }

    protected ResponseCreator withPagedJsonResourceSuccess(String filename, String linkHeaders) {
        HttpHeaders headers = new HttpHeaders();
        if (linkHeaders != null) {
            headers.set("Link", linkHeaders);
        }
        return withJsonResourceSuccess(filename).headers(headers);
    }
}
