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
package org.springframework.social.gitlab.api.profile;

import java.util.Date;
import org.springframework.social.gitlab.api.core.GitlabObject;

/**
 *
 * @author p.hoeffling
 */
public class GitlabProfile extends GitlabObject {

    private long id;

    private String username;

    private String email;

    private String name;

    private String privateToken;

    private String state;

    private Date createdAt;

    private String bio;

    private String skype;

    private String linkedin;

    private String twitter;

    private String websiteUrl;

    private String avatarUrl;

    private long themeId;

    private long colorSchemeId;

    private boolean isAdmin;

    private boolean canCreateGroup;

    private boolean canCreateProject;

    private long projectsLimit;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public String getState() {
        return state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getBio() {
        return bio;
    }

    public String getSkype() {
        return skype;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public long getThemeId() {
        return themeId;
    }

    public long getColorSchemeId() {
        return colorSchemeId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean canCreateGroup() {
        return canCreateGroup;
    }

    public boolean canCreateProject() {
        return canCreateProject;
    }

    public long getProjectsLimit() {
        return projectsLimit;
    }

}
