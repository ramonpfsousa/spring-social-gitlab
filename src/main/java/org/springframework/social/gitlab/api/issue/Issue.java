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
package org.springframework.social.gitlab.api.issue;

import java.util.Date;
import java.util.ArrayList;
import org.springframework.social.gitlab.api.core.GitlabObject;
import org.springframework.social.gitlab.api.user.GitlabUser;

/**
 *
 * @author j.roesler
 */
public class Issue extends GitlabObject {

    private long id;

    private long number;

    private String state;

    private String title;

    private String body;

    private GitlabUser user;

    private ArrayList<Label> labels;

    private GitlabUser assignee;

    private Milestone milestone;

    private long comments;

    private Date closedAt;

    private Date createdAt;

    private Date lastActivityAt;
    
    private GitlabUser closedBy;

    public long getId() {
        return id;
    }

    public long getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public GitlabUser getUser() {
        return user;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public GitlabUser getAssignee() {
        return assignee;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public long getComments() {
        return comments;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getLastActivityAt() {
        return lastActivityAt;
    }

    public GitlabUser getClosedBy() {
        return closedBy;
    }

    public static class Label extends GitlabObject {

        private String name;

        private String color;

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }
    }

    public static class Milestone extends GitlabObject {

        private long id;

        private long number;

        private String state;

        private String title;

        private String description;

        private GitlabUser creator;

        private long openIssues;

        private long closedIssues;

        private Date createdAt;

        private Date lastActivityAt;

        public long getId() {
            return id;
        }

        public long getNumber() {
            return number;
        }

        public String getState() {
            return state;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public GitlabUser getCreator() {
            return creator;
        }

        public long getOpenIssues() {
            return openIssues;
        }

        public long getClosedIssues() {
            return closedIssues;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getLastActivityAt() {
            return lastActivityAt;
        }
    }

}
