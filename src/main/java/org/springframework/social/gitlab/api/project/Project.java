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

import java.util.Date;
import java.util.Set;
import org.springframework.social.gitlab.api.core.GitlabObject;

/**
 *
 * @author p.hoeffling
 */
public class Project extends GitlabObject {

    private long id;

    private String description;

    private String defaultBranch;

    private boolean isPublic;

    private long visibilityLevel;

    private String sshUrlToRepo;

    private String httpUrlToRepo;

    private String webUrl;

    private Owner owner;

    private String name;

    private String nameWithNamespace;

    private String path;

    private String pathWithNamespace;

    private boolean issuesEnabled;

    private boolean mergeRequestsEnabled;

    private boolean wikiEnabled;

    private boolean snippetsEnabled;

    private boolean archived;

    private Namespace namespace;

    private Permissions permissions;

    private Date createdAt;

    private Date lastActivityAt;

    private String avatarUrl;

    private long creatorId;

    private Set<String> tagList;

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public long getVisibilityLevel() {
        return visibilityLevel;
    }

    public String getSshUrlToRepo() {
        return sshUrlToRepo;
    }

    public String getHttpUrlToRepo() {
        return httpUrlToRepo;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getNameWithNamespace() {
        return nameWithNamespace;
    }

    public String getPath() {
        return path;
    }

    public String getPathWithNamespace() {
        return pathWithNamespace;
    }

    public boolean isIssuesEnabled() {
        return issuesEnabled;
    }

    public boolean isMergeRequestsEnabled() {
        return mergeRequestsEnabled;
    }

    public boolean isWikiEnabled() {
        return wikiEnabled;
    }

    public boolean isSnippetsEnabled() {
        return snippetsEnabled;
    }

    public boolean isArchived() {
        return archived;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getLastActivityAt() {
        return lastActivityAt;
    }

    /**
     * Since GitLab 7.9
     *
     * @return
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Since GitLab 7.9
     *
     * @param avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Since GitLab 7.10
     *
     * @return
     */
    public long getCreatorId() {
        return creatorId;
    }

    /**
     * Since GitLab 7.10
     *
     * @param creatorId
     */
    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * Since GitLab 7.10
     *
     * @return
     */
    public Set<String> getTagList() {
        return tagList;
    }

    /**
     * Since GitLab 7.10
     *
     * @param tagList
     */
    public void setTagList(Set<String> tagList) {
        this.tagList = tagList;
    }

    public static class Permissions extends GitlabObject {

        private Access projectAccess;

        private Access groupAccess;

        public Access getProjectAccess() {
            return projectAccess;
        }

        public Access getGroupAccess() {
            return groupAccess;
        }

        public static class Access extends GitlabObject {

            private long accessLevel;

            private long notificationLevel;

            public long getAccessLevel() {
                return accessLevel;
            }

            public long getNotificationLevel() {
                return notificationLevel;
            }
        }

    }

    public static class Namespace extends GitlabObject {

        private long id;

        private String name;

        private String description;

        private String path;

        private long ownerId;

        private Date createdAt;

        private Date updatedAt;

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getPath() {
            return path;
        }

        public long getOwnerId() {
            return ownerId;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

    }

    public static class Owner extends GitlabObject {

        private long id;

        private String name;

        private Date createdAt;

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

    }
}
