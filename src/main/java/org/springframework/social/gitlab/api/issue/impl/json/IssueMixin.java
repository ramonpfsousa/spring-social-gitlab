/*
 * Copyright 2015 j.roesler.
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
package org.springframework.social.gitlab.api.issue.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.social.gitlab.api.core.impl.json.GitlabObjectMixin;
import org.springframework.social.gitlab.api.issue.Issue;
import org.springframework.social.gitlab.api.user.GitlabUser;

/**
 *
 * @author j.roesler
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class IssueMixin extends GitlabObjectMixin {
    
    @JsonProperty("id")
    long id;

    @JsonProperty("number")
    long number;

    @JsonProperty("state")
    String state;

    @JsonProperty("title")
    String title;

    @JsonProperty("body")
    String body;

    @JsonProperty("user")
    GitlabUser user;

    @JsonProperty("labels")
    ArrayList<Issue.Label> labels;

    @JsonProperty("assignee")
    GitlabUser assignee;

    @JsonProperty("milestone")
    Issue.Milestone milestone;

    @JsonProperty("comments")
    long comments;

    @JsonProperty("closed_at")
    Date closedAt;

    @JsonProperty("created_at")
    Date createdAt;

    @JsonProperty("updated_at")
    Date lastActivityAt;
    
    @JsonProperty("losed_by")
    GitlabUser closedBy;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class LabelMixin extends GitlabObjectMixin {

        @JsonProperty("name")
        String name;

        @JsonProperty("color")
        String color;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class MilestoneMixin extends GitlabObjectMixin {

        @JsonProperty("id")
        long id;

        @JsonProperty("number")
        long number;

        @JsonProperty("state")
        String state;

        @JsonProperty("title")
        String title;

        @JsonProperty("description")
        String description;

        @JsonProperty("creator")
        GitlabUser creator;

        @JsonProperty("open_issues")
        long openIssues;

        @JsonProperty("closed_issues")
        long closedIssues;

        @JsonProperty("created_at")
        Date createdAt;

        @JsonProperty("updated_at")
        Date lastActivityAt;
    }
}
