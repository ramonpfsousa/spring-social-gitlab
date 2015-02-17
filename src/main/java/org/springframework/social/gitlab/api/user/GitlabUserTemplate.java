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
package org.springframework.social.gitlab.api.user;

import org.springframework.social.gitlab.api.GitlabUserOperations;
import java.net.URI;
import java.util.List;
import org.springframework.social.gitlab.api.impl.AbstractGitlabTemplate;
import org.springframework.social.gitlab.api.GitlabUriBuilder;
import org.springframework.web.client.RestOperations;

public class GitlabUserTemplate extends AbstractGitlabTemplate implements GitlabUserOperations {

    public GitlabUserTemplate(RestOperations restOperations, GitlabUriBuilder uriBuilder) {
        super(restOperations, uriBuilder);
    }

    @Override
    public GitlabUser getCurrentUser() {
        URI uri = uriBuilder.api().pathSegment("user").build().toUri();
        return restOperations.getForObject(uri, GitlabUser.class);
    }

    @Override
    public GitlabUser getUser(long userId) {
        URI uri = uriBuilder.api().pathSegment("users", "{userId}").buildAndExpand(userId).toUri();
        return restOperations.getForObject(uri, GitlabUser.class);
    }

    @Override
    public List<GitlabSSHKey> getCurrentUsersSSHKeys() {
        URI uri = uriBuilder.api().pathSegment("user", "keys").build().toUri();
        return restOperations.getForObject(uri, GitlabSSHKeyList.class);
    }

    @Override
    public GitlabSSHKey getCurrentUsersSSHKey(long keyId) {
        URI uri = uriBuilder.api().pathSegment("user", "keys", "{keyId}").buildAndExpand(keyId).toUri();
        return restOperations.getForObject(uri, GitlabSSHKey.class);
    }

    
}
