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
import java.util.List;
import org.springframework.social.gitlab.api.core.impl.json.GitlabObjectMixin;
import org.springframework.social.gitlab.api.project.ProjectBranch;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ProjectBranchMixin extends GitlabObjectMixin {

    @JsonProperty("name")
    String name;

    @JsonProperty("protected")
    boolean isProtected;

    @JsonProperty("commit")
    ProjectBranch.Commit commit;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public abstract class CommitMixin extends GitlabObjectMixin {

        @JsonProperty("id")
        String id;

        @JsonProperty("parents")
        List<ProjectBranch.Commit.Parent> parents;

        @JsonProperty("tree")
        String tree;

        @JsonProperty("message")
        String message;

        @JsonProperty("author")
        ProjectBranch.Commit.Author author;

        @JsonProperty("committer")
        ProjectBranch.Commit.Committer committer;

        @JsonProperty("authored_date")
        Date authoredDate;

        @JsonProperty("committed_date")
        Date committedDate;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public abstract class ParentMixin extends GitlabObjectMixin {

            @JsonProperty("id")
            String id;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public abstract class AuthorMixin extends GitlabObjectMixin {

            @JsonProperty("name")
            String name;

            @JsonProperty("email")
            String email;
        }
        
        @JsonIgnoreProperties(ignoreUnknown = true)
        public abstract class CommitterMixin extends GitlabObjectMixin {

            @JsonProperty("name")
            String name;

            @JsonProperty("email")
            String email;
        }
    }

}
