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
package org.springframework.social.gitlab.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectBranch {

    private String name;

    @JsonProperty("protected")
    private boolean isProtected;

    private Commit commit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {

        private String id;

        private List<Parent> parents;

        private String tree;

        private String message;

        private Author author;

        private Committer committer;

        @JsonProperty("authored_date")
        private Date authoredDate;

        @JsonProperty("committed_date")
        private Date committedDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Parent> getParents() {
            return parents;
        }

        public void setParents(List<Parent> parents) {
            this.parents = parents;
        }

        public String getTree() {
            return tree;
        }

        public void setTree(String tree) {
            this.tree = tree;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Committer getCommitter() {
            return committer;
        }

        public void setCommitter(Committer committer) {
            this.committer = committer;
        }

        public Date getAuthoredDate() {
            return authoredDate;
        }

        public void setAuthoredDate(Date authoredDate) {
            this.authoredDate = authoredDate;
        }

        public Date getCommittedDate() {
            return committedDate;
        }

        public void setCommittedDate(Date committedDate) {
            this.committedDate = committedDate;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Parent {

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Author {

            private String name;

            private String email;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Committer {

            private String name;

            private String email;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

        }
    }
}
