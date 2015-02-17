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

import java.util.Date;
import java.util.List;

/**
 *
 * @author p.hoeffling
 */
public class ProjectBranch extends GitlabObject {

    private String name;

    private boolean isProtected;

    private Commit commit;

    public String getName() {
        return name;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public Commit getCommit() {
        return commit;
    }

    public static class Commit extends GitlabObject {

        private String id;

        private List<Parent> parents;

        private String tree;

        private String message;

        private Author author;

        private Committer committer;

        private Date authoredDate;

        private Date committedDate;

        public String getId() {
            return id;
        }

        public List<Parent> getParents() {
            return parents;
        }

        public String getTree() {
            return tree;
        }

        public String getMessage() {
            return message;
        }

        public Author getAuthor() {
            return author;
        }

        public Committer getCommitter() {
            return committer;
        }

        public Date getAuthoredDate() {
            return authoredDate;
        }

        public Date getCommittedDate() {
            return committedDate;
        }

        public static class Parent extends GitlabObject {

            private String id;

            public String getId() {
                return id;
            }
        }

        public static class Author extends GitlabObject {

            private String name;

            private String email;

            public String getName() {
                return name;
            }

            public String getEmail() {
                return email;
            }

        }

        public static class Committer extends GitlabObject {

            private String name;

            private String email;

            public String getName() {
                return name;
            }

            public String getEmail() {
                return email;
            }
        }
    }
}
