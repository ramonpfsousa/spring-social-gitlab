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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author p.hoeffling
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateProjectRequest {

    private final String name;

    private String path;

    private Long namespaceId;

    private String description;

    @JsonProperty("issues_enabled")
    private Boolean issuesEnabled;

    @JsonProperty("merge_requests_enabled")
    private Boolean mergeRequestsEnabled;

    @JsonProperty("wiki_enabled")
    private Boolean wikiEnabled;

    @JsonProperty("snippets_enabled")
    private Boolean snippetsEnabled;

    @JsonProperty("public")
    private Boolean isPublic;

    @JsonProperty("visibility_level")
    private Long visibilityLevel;

    @JsonProperty("import_url")
    private String importUrl;

    public CreateProjectRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(Long namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIssuesEnabled() {
        return issuesEnabled;
    }

    public void setIssuesEnabled(Boolean issuesEnabled) {
        this.issuesEnabled = issuesEnabled;
    }

    public Boolean getMergeRequestsEnabled() {
        return mergeRequestsEnabled;
    }

    public void setMergeRequestsEnabled(Boolean mergeRequestsEnabled) {
        this.mergeRequestsEnabled = mergeRequestsEnabled;
    }

    public Boolean getWikiEnabled() {
        return wikiEnabled;
    }

    public void setWikiEnabled(Boolean wikiEnabled) {
        this.wikiEnabled = wikiEnabled;
    }

    public Boolean getSnippetsEnabled() {
        return snippetsEnabled;
    }

    public void setSnippetsEnabled(Boolean snippetsEnabled) {
        this.snippetsEnabled = snippetsEnabled;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Long getVisibilityLevel() {
        return visibilityLevel;
    }

    public void setVisibilityLevel(Long visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }

    public String getImportUrl() {
        return importUrl;
    }

    public void setImportUrl(String impportUrl) {
        this.importUrl = impportUrl;
    }

}
