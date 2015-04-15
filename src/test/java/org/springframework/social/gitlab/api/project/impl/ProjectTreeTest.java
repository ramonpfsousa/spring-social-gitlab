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
package org.springframework.social.gitlab.api.project.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.social.gitlab.api.AbstractGitlabApiTest;
import org.springframework.social.gitlab.api.project.ProjectBranch;
import org.springframework.social.gitlab.api.project.ProjectTree;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.social.gitlab.api.utils.TestUtils.verifyUtcDate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

public class ProjectTreeTest extends AbstractGitlabApiTest {

    @Test
    public void testGetProjectTrees() {
        String url = uriBuilder.api()
                .pathSegment("projects", "3", "repository", "tree")
                .queryParam("ref_name", "")
                .queryParam("path", "master")
                .toUriString();

        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withJsonResourceSuccess("project-tree"));

        List<ProjectTree> tree = gitlab.projectOperations().getProjecTree(3, "", "master");

        assertThat(tree, is(notNullValue()));
        assertThat(tree, hasSize(6));

        assertThat(tree.get(0).getName(), is("assets"));
        assertThat(tree.get(0).getType(), is("tree"));
        assertThat(tree.get(0).getMode(), is("040000"));
        assertThat(tree.get(0).getId(), is("6229c43a7e16fcc7e95f923f8ddadb8281d9c6c6"));

    }

}
