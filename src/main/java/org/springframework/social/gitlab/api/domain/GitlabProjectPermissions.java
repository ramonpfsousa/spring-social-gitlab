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
package org.springframework.social.gitlab.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitlabProjectPermissions {
    
    @JsonProperty("project_access")
    private GitlabAccess projectAccess;
    
    @JsonProperty("group_access")
    private GitlabAccess groupAccess;

    public GitlabAccess getProjectAccess() {
        return projectAccess;
    }

    public void setProjectAccess(GitlabAccess projectAccess) {
        this.projectAccess = projectAccess;
    }

    public GitlabAccess getGroupAccess() {
        return groupAccess;
    }

    public void setGroupAccess(GitlabAccess groupAccess) {
        this.groupAccess = groupAccess;
    }
    
    
}
