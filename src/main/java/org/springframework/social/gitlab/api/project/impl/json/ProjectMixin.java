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
package org.springframework.social.gitlab.api.project.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.springframework.social.gitlab.api.core.impl.json.GitlabObjectMixin;
import org.springframework.social.gitlab.api.project.Project;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ProjectMixin extends GitlabObjectMixin {

    @JsonProperty("id")
    long id;

    @JsonProperty("description")
    String description;

    @JsonProperty("default_branch")
    String defaultBranch;

    @JsonProperty("public")
    boolean isPublic;

    @JsonProperty("visibility_level")
    long visibilityLevel;

    @JsonProperty("ssh_url_to_repo")
    String sshUrlToRepo;

    @JsonProperty("http_url_to_repo")
    String httpUrlToRepo;

    @JsonProperty("web_url")
    String webUrl;

    @JsonProperty("owner")
    Project.Owner owner;

    @JsonProperty("name")
    String name;

    @JsonProperty("name_with_namespace")
    String nameWithNamespace;

    @JsonProperty("path")
    String path;

    @JsonProperty("path_with_namespace")
    String pathWithNamespace;

    @JsonProperty("issues_enabled")
    boolean issuesEnabled;

    @JsonProperty("merge_requests_enabled")
    boolean mergeRequestsEnabled;

    @JsonProperty("wiki_enabled")
    boolean wikiEnabled;

    @JsonProperty("snippets_enabled")
    boolean snippetsEnabled;

    @JsonProperty("archived")
    boolean archived;

    @JsonProperty("namespace")
    Project.Namespace namespace;

    @JsonProperty("permissions")
    Project.Permissions permissions;

    @JsonProperty("created_at")
    Date createdAt;

    @JsonProperty("last_activity_at")
    Date lastActivityAt;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class PermissionsMixin extends GitlabObjectMixin {

        @JsonProperty("project_access")
        Project.Permissions.Access projectAccess;

        @JsonProperty("group_access")
        Project.Permissions.Access groupAccess;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static abstract class AccessMixin extends GitlabObjectMixin {

            @JsonProperty("access_level")
            long accessLevel;

            @JsonProperty("notification_level")
            long notificationLevel;

        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class NamespaceMixin extends GitlabObjectMixin {

        @JsonProperty("id")
        long id;

        @JsonProperty("name")
        String name;

        @JsonProperty("description")
        String description;

        @JsonProperty("path")
        String path;

        @JsonProperty("owner_id")
        long ownerId;

        @JsonProperty("created_at")
        Date createdAt;

        @JsonProperty("updated_at")
        Date updatedAt;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class OwnerMixin extends GitlabObjectMixin {

        @JsonProperty("id")
        long id;

        @JsonProperty("name")
        String name;

        @JsonProperty("created_at")
        Date createdAt;
    }
}
