package org.springframework.social.gitlab.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitlabProfile {

    private long id;

    private String username;

    private String email;

    private String name;

    @JsonProperty("private_token")
    private String privateToken;

    private String state;

    @JsonProperty("created_at")
    private Date createdAt;

    private String bio;

    private String skype;

    private String linkedin;

    private String twitter;

    @JsonProperty("website_url")
    private String websiteUrl;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("theme_id")
    private long themeId;

    @JsonProperty("color_scheme_id")
    private long colorSchemeId;

    @JsonProperty("is_admin")
    private boolean isAdmin;

    @JsonProperty("can_create_group")
    private boolean canCreateGroup;

    @JsonProperty("can_create_project")
    private boolean canCreateProject;

    @JsonProperty("projects_limit")
    private long projectsLimit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
    public long getThemeId() {
        return themeId;
    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public long getColorSchemeId() {
        return colorSchemeId;
    }

    public void setColorSchemeId(long colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean canCreateGroup() {
        return canCreateGroup;
    }

    public void setCanCreateGroup(boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
    }

    public boolean canCreateProject() {
        return canCreateProject;
    }

    public void setCanCreateProject(boolean canCreateProject) {
        this.canCreateProject = canCreateProject;
    }

    public long getProjectsLimit() {
        return projectsLimit;
    }

    public void setProjectsLimit(long projectsLimit) {
        this.projectsLimit = projectsLimit;
    }

}
