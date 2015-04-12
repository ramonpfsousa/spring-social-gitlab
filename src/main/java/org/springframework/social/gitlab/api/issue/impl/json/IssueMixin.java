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
package org.springframework.social.gitlab.api.issue.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Set;
import org.springframework.social.gitlab.api.core.impl.json.GitlabObjectMixin;
import org.springframework.social.gitlab.api.issue.Issue;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class IssueMixin extends GitlabObjectMixin {

    @JsonProperty("id")
    long id;

    @JsonProperty("iid")
    long iid;

    @JsonProperty("project_id")
    long projectId;

    @JsonProperty("title")
    String title;

    @JsonProperty("description")
    String description;

    @JsonProperty("state")
    String state;

    @JsonProperty("updated_at")
    Date updatedAt;

    @JsonProperty("created_at")
    Date createdAt;

    @JsonProperty("labels")
    Set<String> labels;

    @JsonProperty("milestone")
    Issue.Milestone milestone;

    @JsonProperty("asignee")
    Issue.User asignee;

    @JsonProperty("author")
    Issue.User author;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class MilestoneMixin extends GitlabObjectMixin {

        @JsonProperty("id")
        long id;

        @JsonProperty("title")
        String title;

        @JsonProperty("description")
        String description;

        @JsonProperty("state")
        String state;

        @JsonProperty("due_date")
        Date dueDate;

        @JsonProperty("updated_at")
        Date updatedAt;

        @JsonProperty("created_at")
        Date createdAt;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static abstract class UserMixin extends GitlabObjectMixin {

        @JsonProperty("id")
        long id;

        @JsonProperty("username")
        String username;

        @JsonProperty("email")
        String email;

        @JsonProperty("name")
        String name;

        @JsonProperty("state")
        String state;

        @JsonProperty("created_at")
        Date createdAt;
    }
}
