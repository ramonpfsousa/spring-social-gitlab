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
package org.springframework.social.gitlab.api.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitlabProject {

    private long id;

    private String description;

    @JsonProperty("default_branch")
    private String defaultBranch;

    /**
     * public is a reserverd word...
     */
    @JsonProperty("public")
    private boolean isPublic;

    @JsonProperty("visibility_level")
    private long visibilityLevel;

    @JsonProperty("ssh_url_to_repo")
    private String sshUrlToRepo;

    @JsonProperty("http_url_to_repo")
    private String httpUrlToRepo;

    @JsonProperty("web_url")
    private String webUrl;

    private Owner owner;

    private String name;

    @JsonProperty("name_with_namespace")
    private String nameWithNamespace;

    private String path;

    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;

    @JsonProperty("issues_enabled")
    private boolean issuesEnabled;

    @JsonProperty("merge_requests_enabled")
    private boolean mergeRequestsEnabled;

    @JsonProperty("wiki_enabled")
    private boolean wikiEnabled;

    @JsonProperty("snippets_enabled")
    private boolean snippetsEnabled;

    private boolean archived;

    private Namespace namespace;

    private Permissions permissions;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("last_activity_at")
    private Date lastActivityAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public long getVisibilityLevel() {
        return visibilityLevel;
    }

    public void setVisibilityLevel(long visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }

    public String getSshUrlToRepo() {
        return sshUrlToRepo;
    }

    public void setSshUrlToRepo(String sshUrlToRepo) {
        this.sshUrlToRepo = sshUrlToRepo;
    }

    public String getHttpUrlToRepo() {
        return httpUrlToRepo;
    }

    public void setHttpUrlToRepo(String httpUrlToRepo) {
        this.httpUrlToRepo = httpUrlToRepo;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameWithNamespace() {
        return nameWithNamespace;
    }

    public void setNameWithNamespace(String nameWithNamespace) {
        this.nameWithNamespace = nameWithNamespace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathWithNamespace() {
        return pathWithNamespace;
    }

    public void setPathWithNamespace(String pathWithNamespace) {
        this.pathWithNamespace = pathWithNamespace;
    }

    public boolean isIssuesEnabled() {
        return issuesEnabled;
    }

    public void setIssuesEnabled(boolean issuesEnabled) {
        this.issuesEnabled = issuesEnabled;
    }

    public boolean isMergeRequestsEnabled() {
        return mergeRequestsEnabled;
    }

    public void setMergeRequestsEnabled(boolean mergeRequestsEnabled) {
        this.mergeRequestsEnabled = mergeRequestsEnabled;
    }

    public boolean isWikiEnabled() {
        return wikiEnabled;
    }

    public void setWikiEnabled(boolean wikiEnabled) {
        this.wikiEnabled = wikiEnabled;
    }

    public boolean isSnippetsEnabled() {
        return snippetsEnabled;
    }

    public void setSnippetsEnabled(boolean snippetsEnabled) {
        this.snippetsEnabled = snippetsEnabled;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(Date lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Permissions {

        @JsonProperty("project_access")
        private Access projectAccess;

        @JsonProperty("group_access")
        private Access groupAccess;

        public Access getProjectAccess() {
            return projectAccess;
        }

        public void setProjectAccess(Access projectAccess) {
            this.projectAccess = projectAccess;
        }

        public Access getGroupAccess() {
            return groupAccess;
        }

        public void setGroupAccess(Access groupAccess) {
            this.groupAccess = groupAccess;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Access {

            @JsonProperty("access_level")
            private long accessLevel;

            @JsonProperty("notification_level")
            private long notificationLevel;

            public long getAccessLevel() {
                return accessLevel;
            }

            public void setAccessLevel(long accessLevel) {
                this.accessLevel = accessLevel;
            }

            public long getNotificationLevel() {
                return notificationLevel;
            }

            public void setNotificationLevel(long notificationLevel) {
                this.notificationLevel = notificationLevel;
            }

        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Namespace {

        private long id;

        private String name;

        private String description;

        private String path;

        @JsonProperty("owner_id")
        private long ownerId;

        @JsonProperty("created_at")
        private Date createdAt;

        @JsonProperty("updated_at")
        private Date updatedAt;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public long getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(long ownerId) {
            this.ownerId = ownerId;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {

        private long id;

        private String name;

        @JsonProperty("created_at")
        private Date createdAt;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

    }
}
