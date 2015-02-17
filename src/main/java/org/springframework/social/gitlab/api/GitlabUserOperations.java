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
package org.springframework.social.gitlab.api;

import java.util.List;
import org.springframework.social.gitlab.api.user.GitlabSSHKey;
import org.springframework.social.gitlab.api.user.GitlabUser;

/**
 *
 * @author p.hoeffling
 */
public interface GitlabUserOperations {

    /**
     * Get the currently logged in user.
     * 
     * @return 
     */
    public GitlabUser getCurrentUser();
    
    /**
     * Get an user by id.
     * 
     * @param userId
     * @return 
     */
    public GitlabUser getUser(long userId);

    /**
     * Get the SSH Keys for the currently logged in user.
     * 
     * @return 
     */
    public List<GitlabSSHKey> getCurrentUsersSSHKeys();
    
    /**
     * Get a single SSH Key for the current user.
     * 
     * @param keyId
     * @return 
     */
    public GitlabSSHKey getCurrentUsersSSHKey(long keyId);
    
}
