package org.springframework.social.gitlab.api;

import java.util.Date;

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
